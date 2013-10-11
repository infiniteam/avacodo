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

import org.avacodo.model.LegacyAccount

import static extension org.avacodo.conversion.iban.rules.CheckMethodConfig.*

describe Rule004500 {

	before{subject=new Rule004500}

	fact checkbic(1L)
	fact checkbic(12L)
	fact checkbic(123L)
	fact checkbic(12345L)
	fact checkbic(123456L)
	fact checkbic(1234567L)
	fact checkbic(12345678L)
	fact checkbic(123456789L)
	fact checkbic(1234567890L)


	def checkbic(long konto){
		#[100,200,300,400,500,600,700,800,999].forEach[
			subject.applyTo(new LegacyAccount(it*100000,konto),"09".config).bic should be "ESSEDE5FXXX"
		]
	}
}
