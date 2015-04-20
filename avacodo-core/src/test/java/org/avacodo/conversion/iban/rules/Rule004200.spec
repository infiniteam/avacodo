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

import com.google.common.base.Strings
import org.avacodo.conversion.iban.IbanCreationImpossibleException
import org.avacodo.model.LegacyAccount

import static extension org.avacodo.conversion.iban.rules.CheckMethodConfig.*

describe Rule004200 {

	before{subject=new Rule004200}

	fact notPossible(1L)
	fact notPossible(12L)
	fact notPossible(123L)
	fact notPossible(1234L)
	fact notPossible(12345L)
	fact notPossible(123456L)
	fact notPossible(1234567L)
	fact notPossible(123456789L)
	fact notPossible(1234567890L)

	facts "no iban for xxx00000-xxx00999"{
		#[100,200,300,400,500,600,700,800,999].forEach[pref|
			(0..999).forEach[
				notPossible(asAccount(pref,it))
			]
		]
	}

	facts "pos 4 is 0"{
		#[100,200,300,400,500,600,700,800,999].forEach[pref|
			(1000..9999).forEach[
				possible(asAccount(pref,it))
			]
		]
	}

	facts "pos 4 is not 0"{
		#[100,200,300,400,500,600,700,800,999].forEach[pref|
			(10000..11000).forEach[
				notPossible(asAccount(pref,it))
			]
			(99000..99999).forEach[
				notPossible(asAccount(pref,it))
			]
		]
	}

	fact (50462000..50463999).forEach[possible]

	fact (50469000..50469999).forEach[possible]

	def notPossible(long konto){
		subject.applyTo(new LegacyAccount(12345678,konto),"09".config) should throw IbanCreationImpossibleException
	}

	def possible(long konto){
		subject.applyTo(new LegacyAccount(12345678,konto),"09".config).iban should not be null 
	}
	def asAccount(int pref, int suff){
		Long::parseLong('''«pref»«Strings::padStart(""+suff,5,'0')»''')
	}
}
