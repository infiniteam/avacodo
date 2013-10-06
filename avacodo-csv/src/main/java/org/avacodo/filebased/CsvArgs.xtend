/*******************************************************************************
 * Copyright (C) 2013 infiniteam
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA
 * 
 * infiniteam on GitHub: https://github.com/infiniteam
 ******************************************************************************/
package org.avacodo.filebased

import com.google.common.base.Charsets
import com.google.common.base.Optional
import com.google.common.io.Files
import com.google.common.io.LineProcessor
import java.io.File
import java.io.FileNotFoundException
import java.io.IOException
import java.nio.charset.Charset
import java.util.HashMap
import java.util.List
import java.util.Map
import org.avacodo.conversion.iban.rules.RuleBasedIbanCalculator
import org.avacodo.model.LegacyAccount
import org.eclipse.xtext.xbase.lib.Pair

class CsvArgs{

	File file
	File fileTo
	Charset enc=Charsets.ISO_8859_1
	Integer indexBlz
	Integer indexKonto
	String sep
		
	new(String[] args){
		val argsAsMap=args.argsAsMap
		initFiles(argsAsMap)
		initSeparator(argsAsMap)
		initEncoding(argsAsMap)
		initIndexes(argsAsMap)
		guessIndexes
	}

	def private Map<String, String> argsAsMap(String[]args){
		val result=new HashMap
		if(args.length>0 && !args.get(0).startsWith("-")){
			result.put("i", args.get(0))
			if(args.length>1){
				fillParams(args.tail.toList, result)
			}
		}else{
			fillParams(args.toList, result)
		}
		result
	}

	def void fillParams(List<String> args,  Map<String, String> map){
		if(args.length%2!==0){
			throw new IllegalArgumentException("parameters not well formed")
		}
		(0..<args.size/2).forEach[
			fillParam(args.get(2*it).trim, args.get(2*it+1).trim, map)
		]
	}

	def void fillParam(String key, String value, Map<String, String> map){
		val norm=getNormalized(key)
		if(map.containsKey(norm)){
			throw new IllegalArgumentException("duplicate parameter key "+key)
		}else{
			map.put(norm,value)
		}
	}

	def String getNormalized(String key){
		switch key{
			case "-i":"i"
			case "--input":"i"
			case "-o": "o"
			case "--output":"o"
			case "-a": "a"
			case "--account":"a"
			case "-b": "b"
			case "--bankcode":"b"
			case "-e": "e"
			case "--encoding":"e"
			case "-s": "s"
			case "--separator":"s"
			default: throw new IllegalArgumentException("unkown parameter key "+key)
		}
	}

	def void guessIndexes(){
		val url=this.class.classLoader.getResource("simplified_BLZ2_20130909.txt")
		val configs=new SimpleBankConfigReader(url, Charsets.ISO_8859_1)
		val calc=new RuleBasedIbanCalculator(configs)
		val result=Files.readLines(file, enc, new Counter(calc, indexBlz, indexKonto))

		if(indexBlz===null){
			indexBlz=result.key
			println('''
				Bank code column was not specified. Our guess is that it is
				column «blzIndex+1». If this is not correct, please specify
				the column using the command line parameter "-b <index>". 
			''')
		}
		if(indexKonto===null){
			indexKonto=result.value
			println('''
				Account number column was not specified. Our guess is that it is
				column «indexKonto+1». If this is not correct, please specify
				the column using the command line parameter "-a <index>". 
			''')
		}
	}

	def void initIndexes(Map<String,String> args){
		if(args.get("a")!=null){
			indexKonto=Integer.parseInt(args.get("a"))-1
		}
		if(args.get("b")!=null){
			indexBlz=Integer.parseInt(args.get("b"))-1
		}
	}

	def void initEncoding(Map<String,String> args){
		if(args.get("e")!=null){
			enc=Charset.forName(args.get("e"))
		}
	}

	def void initSeparator(Map<String,String> args){
		if(args.get("s")!=null){
			sep=args.get("s")
		}else{
			sep=";"
		}
	}

	def void initFiles(Map<String,String> args){
		val fileName=args.get("i")
		if(fileName===null){
			throw new IllegalArgumentException("no input file defined")
		}
		file=new File(fileName).absoluteFile
		if(!file.exists){
			throw new FileNotFoundException("file not found "+ fileName)
		}
		if(args.get("o")!=null){
			fileTo=new File(args.get("o")).absoluteFile
		}else{
			fileTo=new File(file.absoluteFile.parentFile, file.name+"_converted")
			if(!fileTo.parentFile.canWrite){
				throw new IllegalArgumentException("missing write access for target directory")
			}
		}
		if(file.equals(fileTo)){
			throw new IllegalArgumentException("input and output file must not be identical")
		}
	}

	def getFile(){file}
	def getFileTo(){fileTo}
	def getEncoding(){enc}
	def getBlzIndex(){indexBlz}
	def getKontoIndex(){indexKonto}
	def getSeparator(){sep}
}


class Counter implements LineProcessor<Pair<Integer,Integer>>{

	Map<Integer, Integer> blzs=newHashMap()
	Map<Integer, Integer> accounts=newHashMap()
	Integer blz
	Integer account
	RuleBasedIbanCalculator calc
	
	new(RuleBasedIbanCalculator calculator, Integer blz, Integer acc) {
		calc=calculator
		this.blz=blz
		this.account=acc
	}
	var int count=0

	override getResult() {
		Optional.fromNullable(blz).or(getBlz)->Optional.fromNullable(account).or(getAccount)
	}
	
	override processLine(String line) throws IOException {
		if(account!==null && blz!==null){
			//no processing necessary
			return false
		}
		if(count>0){
			val split=line.split(";")
			val numbers=(0..split.length-1).filter[split.get(it).isNumber]
			numbers.forEach[initMaps(it)]
			val blzCandidates=numbers.filter[split.get(it).trim.length==8]
			blzCandidates.forEach[
				check(it, numbers, split)
			]
		}
		count=count+1
		count<=100
	}

	def private void check(int blzIndex, Iterable<Integer> accountCandidates, String[] split){
		//if blz index is specified use only that index!
		if(blz==null || blz==blzIndex){
			accountCandidates.forEach[
				//if account index is specified use only that index!!
				if(account==null || (account==it && it!=blzIndex)){
					try{
						val testacc=new LegacyAccount(split.get(blzIndex), split.get(it))
						calc.getIban(testacc)
						blzs.put(blzIndex, blzs.get(blzIndex)+1)
						accounts.put(it, accounts.get(it)+1)
					}catch(Exception e){
					}
				}
			]
		}
	}

	def private void initMaps(int i){
		if(!blzs.containsKey(i)){
			blzs.put(i,0)
		}
		if(!accounts.containsKey(i)){
			accounts.put(i,0)
		}
	}

	def private boolean isNumber(String it){
		try{
			Long.parseLong(it)
			true
		}catch(NumberFormatException e){
			false
		}
	}
	def private getBlz(){
		blzs.keySet.sortBy[-blzs.get(it)].head
	}
	def private getAccount(){
		accounts.keySet.sortBy[-accounts.get(it)].head
	}
}