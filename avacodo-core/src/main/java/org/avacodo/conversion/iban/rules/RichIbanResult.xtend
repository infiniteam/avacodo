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
import org.avacodo.conversion.iban.IbanResult
import org.avacodo.model.BankConfig
import org.avacodo.model.DeIban
import org.avacodo.model.LegacyAccount
import org.avacodo.validation.account.AccountValidationException
import org.avacodo.validation.account.AccountValidator
import org.joda.time.LocalDate

final package class RichIbanResult implements IbanResult{

	private LegacyAccount legAccount
	private boolean overrideAccout
	private boolean overrideBankCode
	private boolean overrideBic
	private String iban
	private String bic
	private boolean suppressValidation
	private BankConfig config

	package new(LegacyAccount account, BankConfig config){
		Preconditions::checkNotNull(account);
		Preconditions::checkNotNull(config);
		legAccount=account
		this.config=config
	}

	def package getAccount(){
		legAccount
	}

	def package validateWith(AccountValidator vali, String method, LocalDate date){
		if (!suppressValidation){
			if(!vali.checkAccountNumber(legAccount.account,method, legAccount.bankCode,date)){
				//we cannot log the account due to security restrictions
				throw new AccountValidationException("account validation failed, method "+method);
			}
		}
	}

	def package overrideAccount(long account){
		if(legAccount.account!=account){
			legAccount=new LegacyAccount(legAccount.bankCode,account)
			overrideAccout=true
		}
	}

	def package overrideBankCode(Integer bankCode){
		if(bankCode!==null && legAccount.bankCode!=bankCode){
			legAccount=new LegacyAccount(bankCode,legAccount.account)
			overrideBankCode=true
		}
	}

	def package suppressValidation(boolean suppressValidation){
		this.suppressValidation=suppressValidation
	}
	def package initDefaultIban(){
		if(!bankCodeReplaced){
			overrideBankCode(config.succeedingBlz)
		}
		iban=DeIban::from(legAccount)
	}

	/**
	 * iban rule overrides returned bic, entries from blz file are ignored
	 */
	def package overrideBic(String bic){
		if(bic!=this.bic){
			overrideBic=true
			setBic(bic)
		}
	}

	def package setBic(String bic){
		this.bic=bic
	}

	def package getConfig(){
		config
	}

	override getBic() {
		bic
	}
	
	override getIban() {
		iban
	}
	
	override isAccountReplaced() {
		overrideAccout
	}
	
	override isBankCodeReplaced() {
		overrideBankCode
	}

	override isBicOverriddenByIbanRule() {
		overrideBic
	}
}