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
package org.avacodo.conversion.iban.rules

import com.google.common.base.Preconditions
import org.avacodo.conversion.iban.IbanCreationImpossibleException
import org.avacodo.conversion.iban.IbanResult
import org.avacodo.model.BankConfig
import org.avacodo.model.LegacyAccount
import org.avacodo.validation.account.AccountValidator
import org.joda.time.LocalDate

abstract package class Rule {

	private static final AccountValidator defaultValidator=AccountValidator::defImpl
	
	private AccountValidator validator=defaultValidator

	def package final IbanResult applyTo(LegacyAccount account, BankConfig config){
		applyTo(account,config , LocalDate::now)
	}

	def package final IbanResult applyTo(LegacyAccount account, BankConfig config, LocalDate date){
		applyTo(new RichIbanResult(account, config), config, date)
	}

	/**
	 * hook for doing rule specific changes to the given account, specific validation rules etc.
	 */
	def package IbanResult applyTo(RichIbanResult result, BankConfig config, LocalDate date)

	/**
	 * validation is done after all replacements have taken place
	 * if the original account must be validated do so in the applyTo method
	 */
	def package final IbanResult defaultApply(RichIbanResult result, String checkMethod, LocalDate date){
		result.validateWith(validator,checkMethod,date)
		result.initDefaultIban
		result
	}

	def package final IbanResult creationNotPossible(RichIbanResult account, String message){
		throw new IbanCreationImpossibleException(message)
	}

	def package final setValidator(AccountValidator validator){
		Preconditions::checkNotNull(validator)
		this.validator=validator
	}

	def protected final getValidator(){
		validator
	}

	/**
	 * compare 10 digit account character position
	 */
	def protected final charAtEqual(RichIbanResult it, int pos, char c){
		account.paddedAccount.charAt(pos).charEqual(c)
	}

	def protected final charEqual(char a, char b){
		return a==b
	}
}