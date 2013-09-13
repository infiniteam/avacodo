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

import org.avacodo.conversion.iban.IbanResult
import org.avacodo.model.DeIban

/**
 * tests whether calculating the iban from account and blz extracted
 * from a prior result stays the same
 */
class DoubleChecker {

	static RuleBasedIbanCalculator calculator=DefaultCalculator.INSTANCE

	def static IbanResult doubleCheck(IbanResult result){
		val account=DeIban::accountFromIban(result.iban)
		val r2=calculator.getIban(account)
		if(r2.iban!=result.iban){
			throw new IllegalStateException("double checking iban failed\n"+result.iban+"\n"+r2.iban)
		}
		//santander replaces bankcode but uses bic of the original one
		//hence bic cannot be obtained correctly from iban
		if(account.bankCode!=31010833){
			if(result.bic!==null && result.bic!="ignore" && r2.bic!=result.bic){
				throw new IllegalStateException("double checking bic failed")
			}
		}
		result
	}
}