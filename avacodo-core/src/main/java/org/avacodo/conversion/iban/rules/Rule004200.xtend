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

package class Rule004200 extends Rule {

	override package applyTo(extension RichIbanResult it, BankConfig config, LocalDate date) {
		if(account.accountLength!=8){
			creationNotPossible("account lenght must be 8")
		}else if(account.account%100000<1000){
			//nnn00000 - nnn00999
			creationNotPossible("unsupported accountRange XXX_000_00 - XXX_009_99")
		}else if(canCreate){
			defaultApply(config.accountCheckMethod,date)
		} else{
			creationNotPossible("unsupported account range")
		}
	}

	def private canCreate(RichIbanResult it){
		val ac=it.account.account
		//pos 4 is index 5
		charAtEqual(5,'0')
			||
		(ac>=50462000 && ac <=50463999)
			||
		(ac>=50469000 && ac <=50469999)
	}
}