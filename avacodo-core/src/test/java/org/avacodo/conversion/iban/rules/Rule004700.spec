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
import org.avacodo.model.LegacyAccount

import static extension org.avacodo.conversion.iban.rules.CheckMethodConfig.*

describe Rule004700 {

	before{subject=new Rule004700}

	//pad left
	fact checkPadding(1L)
	fact checkPadding(12L)
	fact checkPadding(123L)
	fact checkPadding(1234L)
	fact checkPadding(12345L)
	fact checkPadding(123456L)
	fact checkPadding(1234567L)
	fact checkPadding(123456789L)
	fact checkPadding(1234567890L)

	facts "pad 8-digit accounts right"{
		#[100,200,300,400,500,600,700,800,999].forEach[pref|
			(10000..11000).forEach[
				val acc='''«pref»«it»'''
				checkPadding(Long::parseLong(acc),'''«acc»00''')
			]
			(99000..99999).forEach[
				val acc='''«pref»«it»'''
				checkPadding(Long::parseLong(acc),'''«acc»00''')
			]
		]
	}

	def checkPadding(Long konto){
		checkPadding(konto,konto.toString)
	}

	def checkPadding(long konto,String ibanSuffix){
		val padded=Strings::padStart(ibanSuffix,10,'0')
		subject.applyTo(new LegacyAccount(10000000,konto),"09".config).iban should endWith padded
	}
}
