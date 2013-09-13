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

import org.avacodo.conversion.iban.IbanCreationImpossibleException
import org.avacodo.model.LegacyAccount
import org.avacodo.validation.account.AccountValidationException

import static extension org.avacodo.conversion.iban.rules.CheckMethodConfig.*
import static extension org.avacodo.conversion.iban.rules.DoubleChecker.*

describe Rule003100 {

	before{subject=new Rule003100}

	//valid checksum but not a valid account range
	//spec is not explicit as to this case, cKonto says no iban can be created
	fact iban(10120760, 7340000000L,"99") should throw IbanCreationImpossibleException

	//1
	fact iban(54520071, 6790149813L,"99") should be "DE77545201946790149813"
	//2
	fact iban(54520071, 6791000000L,"99") should throw AccountValidationException
	//3
	fact iban(10120760, 897,"99") should throw IbanCreationImpossibleException
	//A
	fact iban(79020325, 1210100047L,"99") should be "DE70762200731210100047"
	//B
	fact iban(70020001, 1210100047L,"95") should be "DE70762200731210100047"
	//C
	fact iban(76020214, 1210100047L,"99") should be "DE70762200731210100047"
	//D rule 32: no deletion announced
	//E bic from bank config file
	//F
	fact iban(63020450, 44613352,"99") should throw IbanCreationImpossibleException

	//G
	fact iban(66020150, 1457032621,"99") should be "DE92660202861457032621"

	//H
	fact iban(79020325, 1210100040,"99") should throw AccountValidationException

	def iban(int blz,long konto, String checkMethod){
		val config=checkMethod.configMarkedDeleted
		subject.applyTo(new LegacyAccount(blz, konto),config).doubleCheck.iban
	}
}