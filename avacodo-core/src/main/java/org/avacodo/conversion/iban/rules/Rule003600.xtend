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

import org.avacodo.model.BankConfig
import org.avacodo.model.LegacyAccount
import org.joda.time.LocalDate

package class Rule003600 extends Rule {
	
	override applyTo(RichIbanResult it, BankConfig config, LocalDate date) {
		validateWith(getValidator,config.accountCheckMethod,date)

		val legAccount=account
		if(legAccount.variante1){
			overrideAccount(account.account*1000)
			overrideBankCode(21050000)
		} else if(legAccount.variante2){
			overrideBankCode(21050000)
		} else{
			return creationNotPossible("unsupported account range")
		}
		initDefaultIban
		it
	}

	def private variante1(LegacyAccount it){
		account>=100_000 && account<=899_999
	}

	def private variante2(LegacyAccount it){
		if(accountLength==8){
			val pos3=paddedAccount.charAt(2)
			pos3.charEqual('3')||pos3.charEqual('4')||pos3.charEqual('5')
		} else if (accountLength==9){
			val pos2=paddedAccount.charAt(1)
			!pos2.charEqual('9')
		}else if(accountLength==10){
			val pos1=paddedAccount.charAt(0)
			val pos12=paddedAccount.substring(0,2)
			!(pos1.charEqual('2') || pos1.charEqual('7') || pos1.charEqual('8'))
				||
			pos12.equals("70") || pos12.equals("85")
		} else{
			false
		}
	}
}