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

import org.avacodo.model.LegacyAccount

import static extension org.avacodo.conversion.iban.rules.CheckMethodConfig.*

describe Rule004600 {

	before{subject=new Rule004600}

	fact checkBlzReplaced(10000000)
	fact checkBlzReplaced(20000000)
	fact checkBlzReplaced(30000000)
	fact checkBlzReplaced(40000000)
	fact checkBlzReplaced(50000000)
	fact checkBlzReplaced(60000000)
	fact checkBlzReplaced(70000000)
	fact checkBlzReplaced(80000000)
	fact checkBlzReplaced(90000000)
	fact checkBlzReplaced(99999999)


	def checkBlzReplaced(int blz){
		val conf="09".config
		#[100L,200L,300L,400L,500L,600L,700L,800L,999L].forEach[
			val result=subject.applyTo(new LegacyAccount(blz,it),conf)
			result.iban.substring(4,12) should be "31010833"
			result.bic should be conf.bic
		]
	}
}