/*
 * #%L
 * Avacodo
 * %%
 * Copyright (C) 2013 - 2014 infiniteam
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
package org.avacodo.conversion.iban.rules

import com.google.common.base.Preconditions
import org.avacodo.model.BankConfig
import org.avacodo.conversion.iban.IbanAmbiguousException
import org.joda.time.LocalDate
import org.avacodo.conversion.iban.IbanUncertainException

package class Rule002000 extends Rule {
	
	override package applyTo(RichIbanResult it, BankConfig config, LocalDate date) {
		val checkMethod=config.accountCheckMethod
		checkDeutscheBank

		//donation account
		if(account.bankCode==50070010 && account.account==9999){
			overrideAccount(92777202)
			return defaultApply(checkMethod,date)
		}

		switch checkMethod{
			case "63":apply63(date)
			case "C7":apply63(date)
			default:defaultApply(checkMethod,date)
		}
	}

	def private apply63(RichIbanResult it, LocalDate date) {

		accountLength10(it, date);

		//valid at all
		validateWith(getValidator,"63",date)
		//check which version worked
		val validOrig=validator.checkAccountNumber(account.account/100,"00", account.bankCode,date)
		val validAdd00=validator.checkAccountNumber(account.account,"00", account.bankCode,date)
		Preconditions::checkState(validOrig||validAdd00, "one of the two methods must have succeeded, implementation error otherwise")

		val accLength=account.accountLength

		return if(accLength<5 || accLength>9){
			//resulting account must have length 7, 8, 9
			//adding 00 to accounts with length less than 5 cannot make them long enough
			creationNotPossible("unsupported account length (5-9 digits)")
		} else if(account.accountLength<7){
			//adding 00 is mandatory
			if(validAdd00){
				overrideAccount(account.account*100)
				defaultApply("09",date)
			}else{
				creationNotPossible("appending 00 to account is mandatory due to length restriction, but for this account validation fails")
			}
		} else if(accLength==7){
			accountLength7(date, validOrig, validAdd00 )
		} else if(validOrig){
			//length>7, hence adding 00 not possible
			defaultApply("09",date)
		} else{
			creationNotPossible("appending 00 is not possible due to length restrictions, but only in this case account validation succeeds")
		}
	}

	def protected accountLength7(RichIbanResult it, LocalDate date, boolean validOrig, boolean validAdd00) {

		//ambiguity possible
		if (validOrig && !validAdd00) {
			defaultApply("09", date)
		} else if (!validOrig && validAdd00) {
			overrideAccount(account.account * 100)
			defaultApply("09", date)
		} else {

			//both are valid
			val result1 = new RichIbanResult(account, config).defaultApply("09", date);
			val result2 = new RichIbanResult(account, config);
			result2.overrideAccount(result2.account.account * 100)
			result2.defaultApply("09", date)
			throw new IbanAmbiguousException(result1, result2,
				"valid account number with and without adding 00 to a 7 digit account")
		}
	}

	def protected accountLength10(RichIbanResult it, LocalDate date) {

		//5.2 check method 63 variant c
		//10-digit account reduce to 9 digits
		if (account.accountLength == 10 && account.paddedAccount.endsWith("000")) {
			if (!getValidator.checkAccountNumber(account.account, "63", account.bankCode, date) &&
				getValidator.checkAccountNumber(account.account / 10, "63", account.bankCode, date)) {
				overrideAccount(account.account / 10)
				throw new IbanUncertainException(defaultApply("63", date),
					"removed last 0 from 10 digit number ending 000");
			}
		}
	}

	def void checkDeutscheBank(RichIbanResult it){
		val bankCode=account.bankCode
		if(bankCode==10020000){
			//5.3 exceptions bank codes without iban
			creationNotPossible("rule explicitly excludes bank code 10020000")
		} else if(!(bankCode==76026000 || bankCode.toString.charAt(3).charEqual('7'))){
			creationNotPossible("not a Deutsche Bank bank code")
		}
	}
}
