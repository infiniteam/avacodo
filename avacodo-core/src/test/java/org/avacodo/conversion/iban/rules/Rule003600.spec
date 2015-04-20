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

describe Rule003600 {

	before{subject=new Rule003600}

	fact notPossible(1L)
	fact notPossible(99999L)
	fact notPossible(900000L)
	fact notPossible(29999999L)
	fact notPossible(900000000L)
	fact notPossible(999999999L)
	fact notPossible(2000000000L)
	fact notPossible(2999999999L)
	fact notPossible(7100000000L)
	fact notPossible(8499999999L)
	fact notPossible(8600000000L)
	fact notPossible(8999999999L)

	fact possible(100000)
	fact possible(899999)
	fact possible(30000000)
	fact possible(59999999)
	fact possible(100000000)
	fact possible(899999999)
	fact possible(1000000000)
	fact possible(1999999999)
	fact possible(3000000000L)
	fact possible(7099999999L)
	fact possible(8500000000L)
	fact possible(8599999999L)
	fact possible(9000000000L)
	fact possible(9999999999L)


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
