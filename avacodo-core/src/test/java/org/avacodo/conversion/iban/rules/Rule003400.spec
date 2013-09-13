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

import static extension org.avacodo.conversion.iban.rules.CheckMethodConfig.*
import static extension org.avacodo.conversion.iban.rules.DoubleChecker.*

describe Rule003400 {

	before{subject=new Rule003400}

	//Q1
	fact iban(60020290, 1320815432,"99") should be "DE69600202901320815432"
	//Q2
	fact iban(60020290, 1457032621,"99") should be "DE92660202861457032621"
	//Q3
	fact iban(60020290, 5951950,"99") should be "DE67600202900005951950"
	//Q4
	fact iban(60020290, 847321750,"99") should throw IbanCreationImpossibleException

	//donation
	fact iban(60020290, 500500500,"99") should be "DE82600202904340111112"
	fact iban(60020290, 502,"99") should be "DE28600202904340118001"


	def iban(int blz,long konto, String checkMethod){
		val config=checkMethod.config
		subject.applyTo(new LegacyAccount(blz, konto),config).doubleCheck.iban
	}
}