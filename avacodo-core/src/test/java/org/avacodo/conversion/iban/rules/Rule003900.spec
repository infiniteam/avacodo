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

import org.avacodo.AvacodoException
import org.avacodo.model.LegacyAccount

import static org.junit.Assert.*

import static extension org.avacodo.conversion.iban.rules.CheckMethodConfig.*
import static extension org.avacodo.conversion.iban.rules.DoubleChecker.*

describe Rule003900 {

	before{subject=new Rule003900}

	val examples='''
		256 213 27
		265 200 17
		265 217 03
		265 223 19
		266 200 10
		266 214 13
		267 200 28
		280 210 02
		280 213 01
		280 215 04
		280 216 23
		280 217 05
		280 219 06
		280 220 15
		280 224 12
		280 225 11
		280 226 20
		280 228 22
		280 232 24
		280 233 25
		282 200 26
		282 222 08
		282 226 21
		283 200 14
		283 218 16
		284 200 07
		284 210 30
		285 200 09
		285 215 18
		291 217 31
	'''.toString.split("[\\r\\n]+").map[Integer::parseInt(it.replaceAll(" ",""))].toList

	facts "blz replaced"{
		examples.forEach[
			try{
				val iban=subject.applyTo(new LegacyAccount(it,13),"09".config).doubleCheck.iban
				iban.substring(4,12) should be "28020050"
			} catch(AvacodoException e){
				fail("iban calculation should not fail "+ it)
			}
		]
	}
}