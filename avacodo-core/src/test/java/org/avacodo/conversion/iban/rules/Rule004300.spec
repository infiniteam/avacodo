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
import org.avacodo.validation.account.AccountValidationException

import static extension org.avacodo.conversion.iban.rules.CheckMethodConfig.*
import static extension org.avacodo.conversion.iban.rules.DoubleChecker.*

describe Rule004300 {	

	before{subject=new Rule004300}

	//Sparkasse Pforzheim Calw 66650085,  check method 06, (SpK Calw 60651070, Methode A9)	
	fact iban(60651070, 868) should be "DE49666500850000000868"
	fact bic(60651070, 868) should be "PZHSDE66XXX"
	
	fact iban(60651070, 12602) should be "DE33666500850000012602"
	fact bic(60651070, 12602) should be "PZHSDE66XXX"
	
	fact iban(60651070, 86725) should throw AccountValidationException

	def iban(int blz,long konto ) {
		subject.applyTo(new LegacyAccount(blz, konto),"A9".config).doubleCheck.iban
	}
	
	def bic(int blz,long konto){
		subject.applyTo(new LegacyAccount(blz, konto),"A9".config).bic
	}
}
