/*
 * #%L
 * Avacodo
 * %%
 * Copyright (C) 2013 - 2015 infiniteam
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as 
 * published by the Free Software Foundation, either version 2.1 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 * #L%
 */
package org.avacodo.filebased

import com.google.common.collect.ImmutableSet
import com.google.common.io.Resources
import java.net.URL
import java.nio.charset.Charset
import java.util.List
import java.util.Map
import org.avacodo.model.BankConfig
import org.avacodo.model.BankConfigRepository
import org.avacodo.model.UnknownBankCodeException
import org.eclipse.xtext.xbase.lib.Pair
import org.joda.time.LocalDate

/**
 * does not support obtaining configurations for a particular date, only the initialized file!!
 * currently does not support re-initialization with an updated file
 */
class SimpleBankConfigReader implements BankConfigRepository {

	var private Map<Integer, BankConfig> theMap

	new(URL blzFile, Charset encoding){
		init(blzFile, encoding)
	}

	def private void init(URL blzFile, Charset encoding){
		init(Resources::readLines(blzFile, encoding))
		ensureInitialized
		//allows transforming bankconfig file into a simplified version containing only the relevant data
		if(!Resources::readLines(blzFile, encoding).get(0).startsWith("simplifiedBLZ")){
//			writeSimplifiedBlz(blzFile,encoding)
		}
	}

//	def private writeSimplifiedBlz(URL url, Charset encoding) {
//		val file=new File(url.file)
//		println('''«file.absolutePath» «file.exists»''')
//		val simpFile=new File(file.absoluteFile.parent,'''simplified_«file.name»''')
//		if(!simpFile.exists){
//			val sorted=theMap.keySet.sort
//			Files::write('''
//				simplifiedBLZ
//				«FOR blz:sorted»
//					«blz»;«theMap.get(blz).simplified»
//				«ENDFOR»
//			''',simpFile, encoding)
//		} 
//	}
//
//	def private simplified(BankConfig c)'''
//		«c.accountCheckMethod»;«c.ibanRule»«Strings::padStart(c.ibanRuleVersion.toString,2,'0')»;«c.deletionAnnounced»;«c.bic»;«Optional::fromNullable(c.succeedingBlz).or(0)»
//	'''

	override getBankConfig(int bankCode) {
		val config=theMap.get(bankCode)
		if(config===null){
			throw new UnknownBankCodeException("unknown bank code "+bankCode)
		}else{
			config
		}
	}

	/**
	 * ignores the given date
	 */
	@Deprecated
	override getBankConfig(int bankCode, LocalDate date) {
		getBankConfig(bankCode)
	}

	def package getKnownBankCodes(){
		ImmutableSet::copyOf(theMap.keySet)
	}

	def private ensureInitialized() {
		if(theMap===null || theMap.size==0){
			throw new IllegalStateException("blz file has not been initialized properly")
		}
	}


	def private void init(List<String> lines){
		theMap=null
		val result=newHashMap
		if(lines.size>0 && lines.get(0).startsWith("simplifiedBLZ")){
			lines.tail.forEach[
				if(trim.length>0){
					val split=split(";")
					result.put(
						Integer::parseInt(split.get(0)),
							BankConfig::builder
							.checkMethod(split.get(1))
							.ibanRule(split.get(2))
							.deletionAnnounced(split.get(3)=="true")
							.bic(split.get(4))
							.succeedingBlz(split.get(5))
							.build
						)
				}
			]
		}else{
			lines.forEach[
				if(trim.length>0){
					addNonDeletedEntry(result)
				}
			]
			result.values.filter[succeedingBlz!==null].forEach[
				val successor=result.get(succeedingBlz)
				if(successor.succeedingBlz!==null){
					throw new IllegalStateException("successor bank code may not have a successor itself")
				}
			]
		}
		theMap=result
	}

	def private addNonDeletedEntry(String it, Map<Integer,BankConfig> result){
		val newEntry=getEntry
		if(newEntry!==null){
			val oldEntry=result.get(newEntry.key)
			if(!isPrimary){
				if(oldEntry===null){
					throw new IllegalStateException("there cannot be two primary entries for the same bank code "+newEntry.key)
				}else if(!oldEntry.match(newEntry.value)){
					throw new IllegalStateException("primary and non-primary entries do not match for bank code "+newEntry.key)
				}
			} else{
				if(oldEntry!==null){
					throw new IllegalStateException("missing primary entry for "+ newEntry.key)
				}
				result.put(newEntry.key,newEntry.value)
			}
		}
	}

	def package final boolean match(BankConfig a, BankConfig b){
		a.deletionAnnounced==b.deletionAnnounced && 
		a.ibanRule==b.ibanRule && 
		a.ibanRuleVersion==b.ibanRuleVersion
		a.succeedingBlz == b.succeedingBlz
		a.accountCheckMethod == b.accountCheckMethod
		//bic does differ in non-primary blz
//		a.bic==b.bic
	}

	/**
	 * null if entry is actually deleted
	 */
	def package final Pair<Integer, BankConfig> getEntry(String s){
		val deleted=s.substring(158,159)
		if(deleted!="D"){
			val blz=Integer::parseInt(s.substring(0,8))
			val checkMethod=s.substring(150,152)
			val deletionAnnounced=s.substring(159,160)
			val bic=s.substring(139,150)
			val successor=s.substring(160,168)
			val ibanRule=s.substring(168,174)
			val delAnnounced=deletionAnnounced.charAt(0).charEqual('1')
			val config=BankConfig::builder
				.checkMethod(checkMethod)
				.ibanRule(ibanRule)
				.deletionAnnounced(delAnnounced)
				.bic(bic)
				.succeedingBlz(successor)
				.build
			blz->config
		}else{
			null
		}
	}

	def package final isPrimary(String s){
		val prime=s.charAt(8)
		prime.charEqual('1')
	}

	def package final charEqual(char a, char b){
		return a==b
	}
}
