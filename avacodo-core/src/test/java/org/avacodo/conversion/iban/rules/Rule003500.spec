/*
 * #%L
 * Avacodo
 * %%
 * Copyright (C) 2013 - 2015 infiniteam
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

import org.avacodo.conversion.iban.IbanCreationImpossibleException
import org.avacodo.model.LegacyAccount

import static extension org.avacodo.conversion.iban.rules.CheckMethodConfig.*
import static extension org.avacodo.conversion.iban.rules.DoubleChecker.*

describe Rule003500 {

	before{subject=new Rule003500}

	//R1
	fact iban(79020076, 1050958422,"99") should be "DE42790200761050958422"
	//R2
	fact iban(79020076, 1320815432,"99") should be "DE69600202901320815432"
	//R3
	fact iban(79020076, 5951950,"99") should be "DE56790200760005951950"
	//R4
	fact iban(79020076, 847321750,"99") should throw IbanCreationImpossibleException

	//donation
	fact iban(79020076, 9696,"99") should be "DE29790200761490196966"


	def iban(int blz,long konto, String checkMethod){
		val config=checkMethod.config
		subject.applyTo(new LegacyAccount(blz, konto),config).doubleCheck.iban
	}
}
