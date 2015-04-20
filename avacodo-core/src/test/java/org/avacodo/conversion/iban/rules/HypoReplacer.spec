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

describe HypoReplacer {

	fact HypoReplacer::getKreis(1234567890) should be 123
	fact HypoReplacer::getKreis(123456789) should be 12
	fact HypoReplacer::getKreis(12345678) should be 1
	fact HypoReplacer::getKreis(1234567) should be 0
	fact HypoReplacer::getKreis(123456) should be 0
	
	fact replace(54520071,6790149813L) should be 54520194
	fact replace(12345678,1491234567) should be 79020076
	fact replace(12345678,1841234567) should throw IbanCreationImpossibleException

	def private replace(int blz, long account){
		val result=new RichIbanResult(new LegacyAccount(blz,account),"09".config)
		HypoReplacer::overrideByAccount(result)
		result.account.bankCode
	}
}
