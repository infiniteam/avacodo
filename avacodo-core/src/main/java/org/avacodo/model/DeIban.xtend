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
package org.avacodo.model

import com.google.common.base.Preconditions
import com.google.common.base.Strings
import java.math.BigInteger

class DeIban {

	def static from(LegacyAccount account){
		return fromString('''«account.getBankCode»«account.paddedAccount»''')
	}

	def static boolean validate(String iban){
		Preconditions::checkArgument(iban!==null && iban.length==22, "iban must have length 22")
		return iban.equals(fromString(iban.substring(4)))
	}

	def static LegacyAccount accountFromIban(String iban){
		new LegacyAccount(iban.substring(4,12),iban.substring(12,22))
	}

	def private static fromString(String blzaccount){
		val infix=blzaccount
		val checkSum=(98bi-new BigInteger('''«infix»131400''').mod(97bi)).toString
		return '''DE«Strings::padStart(checkSum,2,'0')»«infix»'''
	}
}