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

describe Rules {

	fact Rules::values.forEach[
		it should match rule.class.simpleName
	]

	def private should_match(Rules r, String className){
		className should be '''Rule«r.name.subSequence(1,5)»«r.name.subSequence(6,8)»'''
	}

	fact Rules::from("000000".config) should be Rules::R0000_00.rule

	fact Rules::from(0.config) should be Rules::R0000_00.rule

	fact Rules::from("004200".config) should be Rules::R0042_00.rule

	fact Rules::from(4200.config) should be Rules::R0042_00.rule

	fact Rules::from(3501.config) should be Rules::R0035_01.rule


	def config(Object iban){
		val rule=switch iban{
			String:Integer::parseInt(iban)
			Integer:iban
		}
		BankConfig::builder.checkMethod("XX").ibanRule(rule).bic("tada").build
	}
}