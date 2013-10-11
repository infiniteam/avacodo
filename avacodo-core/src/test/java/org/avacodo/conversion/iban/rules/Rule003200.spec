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
import org.avacodo.validation.account.AccountValidationException

import static extension org.avacodo.conversion.iban.rules.CheckMethodConfig.*
import static extension org.avacodo.conversion.iban.rules.DoubleChecker.*

describe Rule003200 {

	before{subject=new Rule003200}

	//J
	fact iban(76220073, 1210100047,"99") should be "DE70762200731210100047"
	//K
	fact iban(66020286, 1457032621,"99") should be "DE92660202861457032621"
	//L
	fact iban(76220073, 3200000012L,"99") should be "DE06710221823200000012"
	//M
	fact iban(10020890, 5951950,"99") should be "DE07100208900005951950"
	//N
	fact iban(10020890, 897,"99") should throw AccountValidationException
	//O
	fact iban(85020086, 847321750,"99") should throw IbanCreationImpossibleException


	def iban(int blz,long konto, String checkMethod){
		val config=checkMethod.config
		subject.applyTo(new LegacyAccount(blz, konto),config).doubleCheck.iban
	}
}
