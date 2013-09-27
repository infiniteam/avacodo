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

	def static void main(String[] args) {
		val fileNameFrom=args.get(0)
		val encoding=Charset::forName(args.get(1))
		val blzIndex=Integer::parseInt(args.get(2))-1
		val kontoIndex=Integer::parseInt(args.get(3))-1
		val fileNameTo=args.get(4)

		val File result=new File(fileNameTo)
		result.delete
		Files::touch(result)
		val File source=new File(fileNameFrom)
		Files::readLines(source, encoding, new SimpleCsvConverter(result, encoding, blzIndex, kontoIndex))
	}

	new(){
		val url=this.class.classLoader.getResource("simplified_BLZ2_20130909.txt")
		configs=new SimpleBankConfigReader(url, Charset::forName("ISO-8859-1"))
		calc=new RuleBasedIbanCalculator(configs)
	}

	private new(File outputFile, Charset encoding, int blzIddex, int kontoIndex){
		this()
		out=outputFile;
		enc=encoding
		indexBlz=blzIddex
		indexKonto=kontoIndex
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
			Files::append('''«it»;IBAN;BIC;message/error''',out, enc)
			first=false;
		}else{
			val split=split(";")
			var LegacyAccount account
			try{
				account=new LegacyAccount(split.get(indexBlz),split.get(indexKonto))
				val iban=account.result
				append('''«it»;«iban.iban»;«iban.bic»;''')
			} catch(UnknownBankCodeException e){
				append('''«it»;;;BANK CODE NOT KNOWN''')
			} catch(AccountValidationException e){
				val config=configs.getBankConfig(account.bankCode)
				append('''«it»;;;ERROR VALIDATING ACCOUNT - CHECK METHOD «config.accountCheckMethod»''')
			} catch(IbanAmbiguousException e){
				append('''«it»;«e.iban.iban»;«e.iban.bic»;AMBIGUOUS IBAN!! («e.iban2.iban» «e.iban2.bic»»)''')
			} catch(IbanUncertainException e){
				append('''«it»;«e.iban.iban»;«e.iban.bic»;IBAN NOT CERTAIN!!''')
			}catch(Exception e){
				append('''«it»;;;«e.message»''')
			}
		}
		true
	}

	def getResult(LegacyAccount account){
		calc.getIban(account)
	}
}