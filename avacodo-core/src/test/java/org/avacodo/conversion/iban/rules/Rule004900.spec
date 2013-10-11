/*
 * #%L
 * Avacodo
 * %%
 * Copyright (C) 2013 infiniteam
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

import org.avacodo.model.BankConfig
import org.avacodo.model.LegacyAccount
import org.avacodo.validation.account.AccountValidationException

import static extension org.avacodo.conversion.iban.rules.DoubleChecker.*

describe Rule004900{

	before{subject=new Rule004900}

	// examples from IBAN rule documentation	
	fact iban(30060010, 1991182 ) should be "DE26300600109911820001"
	fact iban(30060010, 36) should be "DE57300600100002310113"
	fact iban(40060000, 936) should be "DE39400600000002310113"
	fact iban(40060000, 999) should be "DE89400600000001310113"
	fact iban(57060000, 6060) should be "DE50570600000000160602"
	// others
	fact iban(57060000, 2618040504L) should be "DE36570600002618040504"
	fact iban(57060000, 2618040505L) should throw AccountValidationException
	fact iban(30060010, 1971182) should be "DE35300600109711820001"
	fact iban(30060010, 9999899992L) should endWith "9999899992"
	fact iban(30060010, 1871180L) should endWith "1871180"

	fact subject.moveFirst4ToEnd(1991182) should be 9911820001L	
	fact subject.moveFirst4ToEnd(1971182) should be 9711820001L
	fact subject.moveFirst4ToEnd(1871182) should be 8711820001L
	fact subject.moveFirst4ToEnd(1234908765L) should be 9087651234L
	fact subject.moveFirst4ToEnd(99999) should be 999990000L
	fact subject.moveFirst4ToEnd(9999899999L) should be 8999999999L
	fact subject.moveFirst4ToEnd(2618040504L) should be 405042618L

	def iban(int blz,long konto){
		val result=subject.applyTo(new LegacyAccount(blz, konto), config)
		// special rule where account number is modified
		if(konto % 1000000 < 900000){
			result.doubleCheck
		}
		result.iban
	}
	def private config(){
		BankConfig::builder.checkMethod("44").ibanRule(4900).bic("tada").build
	}
}
