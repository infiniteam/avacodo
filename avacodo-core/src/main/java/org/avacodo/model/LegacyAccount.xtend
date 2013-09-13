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

import com.google.common.base.Objects
import com.google.common.base.Preconditions
import com.google.common.base.Strings

class LegacyAccount {
	private int bankCode
	private long account

	new(int bankCode, long account){
		this.bankCode=bankCode
		this.account=account
		Preconditions::checkArgument(bankCode>=100_000_00 && bankCode<=999_999_99, "bank codes must have 8 digits")
		Preconditions::checkArgument(account>=1 && account<=999_999_999_9L, "account outside supported range")
	}
	new(String bankCode, String account){
		this(Integer::parseInt(bankCode), Long::parseLong(account))
	}

	/**
	 * guaranteed to be of length 8
	 */
	def final getBankCode(){
		bankCode
	}

	def final getAccount(){
		account
	}

	def final accountLength(){
		(""+account).length
	}

	def final paddedAccount(){
		Strings::padStart(""+account,10,'0')
	}

	override hashCode(){
		Objects::hashCode(bankCode, account)
	}

	override equals(Object o){
		switch (o){
			LegacyAccount: o.bankCode==bankCode && o.account==account
			default:false
		}
	}

	override toString(){
		Objects::toStringHelper(this).add("bankcode", bankCode).add("account", account).toString
	}
}