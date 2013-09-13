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
package org.avacodo.model

describe BankConfig {

	fact ibanRuleFor(0) should be 0
	fact ibanRuleFor("1") should be 0
	fact ibanRuleFor("000000") should be 0

	fact ibanRuleFor(98) should be 0
	fact ibanRuleFor("99") should be 0
	fact ibanRuleFor("000099") should be 0

	fact ibanRuleFor(4200) should be 42
	fact ibanRuleFor("4201") should be 42
	fact ibanRuleFor("004200") should be 42

	fact ibanRuleFor(94254) should be 942
	fact ibanRuleFor("94253") should be 942
	fact ibanRuleFor("094254") should be 942


	fact ibanVersionFor(0) should be 0
	fact ibanVersionFor("1") should be 1
	fact ibanVersionFor("99") should be 99
	fact ibanVersionFor(4200) should be 0
	fact ibanVersionFor("4201") should be 1
	fact ibanVersionFor("004299") should be 99
	fact ibanVersionFor(876543) should be 43
	fact ibanVersionFor("987654") should be 54

	fact succeedingBlz(null) should be null as String
	fact succeedingBlz("12345678") should be 12345678
	fact succeedingBlz("00000000") should be null as String
	fact succeedingBlz("87654321") should be 87654321
	fact succeedingBlz("0") should be null as String

	def succeedingBlz(String successor){
		config.succeedingBlz(successor).build.succeedingBlz
	}

	def ibanRuleFor(String s){
		config.ibanRule(s).build.ibanRule
	}
	def ibanRuleFor(int i){
		config.ibanRule(i).build.ibanRule
	}

	def ibanVersionFor(String s){
		config.ibanRule(s).build.ibanRuleVersion
	}
	def ibanVersionFor(int i){
		config.ibanRule(i).build.ibanRuleVersion
	}

	def config(){
		BankConfig::builder.ibanRule("0").checkMethod("XX").bic("tada")
	}
}