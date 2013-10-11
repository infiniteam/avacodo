/*
 * #%L
 * Avacodo CSV
 * %%
 * Copyright (C) 2013 infiniteam
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

import com.google.common.io.Files
import com.google.common.io.LineProcessor
import java.io.File
import java.io.IOException
import java.nio.charset.Charset
import org.avacodo.conversion.iban.IbanAmbiguousException
import org.avacodo.conversion.iban.IbanUncertainException
import org.avacodo.conversion.iban.rules.RuleBasedIbanCalculator
import org.avacodo.model.LegacyAccount
import org.avacodo.model.UnknownBankCodeException
import org.avacodo.validation.account.AccountValidationException
import com.google.common.base.Charsets
import java.io.FileNotFoundException
import java.nio.charset.UnsupportedCharsetException

/**
 * very simple converter taking a csv file (separator ; no string escaping) appending 3 fields iban, bic, conversion/error message
 * 
 * the csv file needs exactly one header line
 * 
 * parameter 1: source file
 * parameter 2: encoding (ISO-8859-1, UTF-8, ...)
 * parameter 3: index of blz column (counting starts with 1)
 * parameter 4: index of account column (counting starts with 1)
 * parameter 5: target file
 */
class SimpleCsvConverter implements LineProcessor<Void>{

	private SimpleBankConfigReader configs
	private RuleBasedIbanCalculator calc
	private boolean first=true
	private File out
	private Charset enc
	private int indexBlz
	private int indexKonto
	private String sep

	def static void main(String[] args) {
		val extension parsed=parse(args)
		fileTo.delete
		Files::touch(fileTo)
		Files::readLines(
			file, 
			encoding, 
			new SimpleCsvConverter(fileTo, encoding, blzIndex, kontoIndex, separator)
		)
	}
	def private static parse(String[] args){
		try{
			new CsvArgs(args)
		}catch(FileNotFoundException e){
			handleException(e)
		}catch(IllegalArgumentException e){
			handleException(e)
		}catch(UnsupportedCharsetException e){
			handleException(e)
		}
	}

	def private static handleException(Exception e){
		System.err.println('''
			Error processing command line parameters: «e.message»

			Usage:
			[-i] <file to be converted> (--input)
			[-e <encoding of the file>] (--encoding)
			[-s <colum separator>] (--separator)
			[-b <column containing the bank code>] (--bankcode)
			[-a <column containing the account number>] (--account)
			
			Parameter Example:
			
			test.csv
			-i temp/test.csv --encoding UTF-8 -a 3 -s ,
			test.csv -bankcode 1 -o result.csv

			General information:
			We can only process csv files without string escaping, i.e. columns may not contain the separator.
			The default separator is semicolon (;).
			Column count starts with 1.
		''')
		System.exit(1)
	}

	new(){
		val url=this.class.classLoader.getResource("simplified_BLZ2_20130909.txt")
		configs=new SimpleBankConfigReader(url, Charsets.ISO_8859_1)
		calc=new RuleBasedIbanCalculator(configs)
	}

	private new(File outputFile, Charset encoding, int blzIddex, int kontoIndex, String separator){
		this()
		out=outputFile
		enc=encoding
		indexBlz=blzIddex
		indexKonto=kontoIndex
		sep=separator
	}
	override getResult() {
		return null
	}
	
	def private append(String line){
		Files::append("\n",out, enc)
		Files::append(line,out, enc)
	}
	override processLine(String it) throws IOException {
		if(first){
			Files::append('''«it»«sep»IBAN«sep»BIC«sep»message/error''',out, enc)
			first=false
		}else{
			val split=split(sep)
			var LegacyAccount account
			try{
				account=new LegacyAccount(split.get(indexBlz),split.get(indexKonto))
				val iban=account.result
				append('''«it»«sep»«iban.iban»«sep»«iban.bic»«sep»''')
			} catch(UnknownBankCodeException e){
				append('''«it»«sep»«sep»«sep»BANK CODE NOT KNOWN''')
			} catch(AccountValidationException e){
				val config=configs.getBankConfig(account.bankCode)
				append('''«it»«sep»«sep»«sep»ERROR VALIDATING ACCOUNT - CHECK METHOD «config.accountCheckMethod»''')
			} catch(IbanAmbiguousException e){
				append('''«it»«sep»«e.iban.iban»«sep»«e.iban.bic»«sep»AMBIGUOUS IBAN!! («e.iban2.iban» «e.iban2.bic»»)''')
			} catch(IbanUncertainException e){
				append('''«it»«sep»«e.iban.iban»«sep»«e.iban.bic»«sep»IBAN NOT CERTAIN!!''')
			}catch(Exception e){
				append('''«it»«sep»«sep»«sep»«e.message»''')
			}
		}
		true
	}

	def getResult(LegacyAccount account){
		calc.getIban(account)
	}
}
