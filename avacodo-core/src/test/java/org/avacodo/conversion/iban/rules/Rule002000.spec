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

import org.avacodo.conversion.iban.IbanAmbiguousException
import org.avacodo.conversion.iban.IbanCreationImpossibleException
import org.avacodo.conversion.iban.IbanUncertainException
import org.avacodo.model.BankConfig
import org.avacodo.model.LegacyAccount
import org.avacodo.validation.account.AccountValidationException

import static org.junit.Assert.*

import static extension org.avacodo.conversion.iban.rules.DoubleChecker.*

describe Rule002000 {

	before{subject=new Rule002000}

	val static final _63="63"
	val static final _C7="C7"

	//donation account
	fact iban(50070010, 9999,_63) should be "DE80500700100092777202"

	//eindeutig
	fact iban(50070010,3500022, _63) should endWith "0350002200"
	fact iban(50070010,38150900, _63) should endWith "0038150900"
	fact iban(50070010,600103660, _63) should endWith "0600103660"
	fact iban(50070010,39101181, _63) should endWith "0039101181"
	fact iban(50070010,56002, _63) should endWith "0005600200"
	fact iban(50070010,3700246, _63) should endWith "0003700246"
	fact iban(50070010,6723143, _63) should endWith "0006723143"
	fact iban(50070010,9000043, _63) should endWith "0900004300"

	//TODO test data in spec seems to be incorrect
	//account validates without appending, however the account is too short, so appending is necessary
	fact iban(50070010,752691, _63) should endWith "0075269100"

	//7-digit ambiguity
	fact iban(50070010,5719083, _63) should throw IbanAmbiguousException
	fact iban(50070010,8007759, _63) should throw IbanAmbiguousException

	fact "ambiguous iban"{
		try{
			iban(50070010,5719083, _63)
			fail
		}catch(IbanAmbiguousException e){
			e.iban.iban should endWith "0005719083"
			e.iban.bic should be null as String
			e.iban2.iban should endWith "0571908300"
			e.iban2.bic should be null as String
		}
	}
	//10-digit remove last 0
	fact "uncertain iban 1"{
		try{
			iban(50070010,1415900000L, _63)
			fail
		}catch(IbanUncertainException e){
			e.iban.iban should endWith "0141590000"
			e.iban.bic should be null as String
		}
	}
	fact "uncertain iban 2"{
		try{
			iban(50070010,4287900000L, _63)
			fail
		}catch(IbanUncertainException e){
			e.iban.iban should endWith "0428790000"
			e.iban.bic should be null as String
		}
	}
	fact "uncertain iban 3"{
		try{
			iban(50070010,4915518000L, _63)
			fail
		}catch(IbanUncertainException e){
			e.iban.iban should endWith "0491551800"
			e.iban.bic should be null as String
		}
	}
	fact "uncertain iban 4"{
		try{
			iban(50070010,5814488000L, _63)
			fail
		}catch(IbanUncertainException e){
			e.iban.iban should endWith "0581448800"
			e.iban.bic should be null as String
		}
	}
	fact "uncertain iban 5"{
		try{
			iban(50070010,3805868000L, _63)
			fail
		}catch(IbanUncertainException e){
			e.iban.iban should endWith "0380586800"
			e.iban.bic should be null as String
		}
	}

	//invalid
	fact iban(50070010,94012341, _63) should throw AccountValidationException
	fact iban(50070010,5073321010L, _63) should throw AccountValidationException
	fact iban(50070010,123456700L, _63) should throw AccountValidationException
	fact iban(50070010,1000300004L, _63) should throw AccountValidationException
	//too short
	fact iban(50070010,117, _63) should throw IbanCreationImpossibleException
	fact iban(50070010,500, _63) should throw AccountValidationException
	fact iban(50070010,1800, _63) should throw IbanCreationImpossibleException

	//C7
	fact iban(76026000,94012341, _C7) should throw IbanCreationImpossibleException
	fact iban(76026000,5073321010L, _C7) should throw IbanCreationImpossibleException


	fact iban(76026000,1234517892, _C7) should throw AccountValidationException
	fact iban(76026000,987614325, _C7) should throw AccountValidationException

	def iban(int blz,long konto, String checkMethod){
		val result=subject.applyTo(new LegacyAccount(blz, konto),checkMethod.config)
		//appending 00 to 56002 makes it an ambiguous account, so double checking fails
		if(konto!=56002){
			result.doubleCheck
		}
		result.iban
	}
	def private config(String checkmethod){
		BankConfig::builder.checkMethod(checkmethod).ibanRule(2000).bic("tada").build
	}
}
