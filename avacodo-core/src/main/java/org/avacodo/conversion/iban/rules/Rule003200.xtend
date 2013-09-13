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

import org.joda.time.LocalDate
import org.avacodo.model.BankConfig

package class Rule003200 extends Rule {

	override package applyTo(RichIbanResult it, BankConfig config, LocalDate date) {
		if(config.deletionAnnounced){
			creationNotPossible("bank code must not be announced for deletion "+account.bankCode)
		}else if(account.accountLength==10){
			HypoReplacer::overrideByAccount(it)
			defaultApply(config.accountCheckMethod,date)
		}else if(account.account>=800_000_000 && account.account<=899_999_999){
			creationNotPossible("unsupported account range 800_000_000-899_999_999")
		} else {
			defaultApply(config.accountCheckMethod,date)
		}
	}
}