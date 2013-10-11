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

import org.avacodo.conversion.iban.IbanCreationImpossibleException
import org.avacodo.model.LegacyAccount

import static extension org.avacodo.conversion.iban.rules.CheckMethodConfig.*
import static extension org.avacodo.conversion.iban.rules.DoubleChecker.*

describe Rule003300 {

	before{subject=new Rule003300}

	//P1
	fact iban(70020270, 2950219435L,"95") should be "DE76700202702950219435"
	//P2
	fact iban(70020270, 1457032621,"95") should be "DE92660202861457032621"
	//P3
	fact iban(70020270, 897,"95") should be "DE55700202700000000897"
	//P4
	fact iban(70020270, 847321750,"95") should be "DE36700202700847321750"
	//P5
	fact iban(72020270, 847321750,"23") should throw IbanCreationImpossibleException

	//donation
	fact iban(70020270, 22222,"95") should be "DE11700202705803435253"
	fact iban(70020270, 1111111,"95") should be "DE88700202700039908140"
	fact iban(70020270, 94,"95") should be "DE83700202700002711931"
	fact iban(70020270, 7777777,"95") should be "DE40700202705800522694"
	fact iban(70020270, 55555,"95") should be "DE61700202705801800000"


	def iban(int blz,long konto, String checkMethod){
		val config=checkMethod.config
		subject.applyTo(new LegacyAccount(blz, konto),config).doubleCheck.iban
	}
}
