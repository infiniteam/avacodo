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

describe LegacyAccount {

	before{
		subject = new LegacyAccount(12345678,1)
	}

	def account(String blz, String account){
		new LegacyAccount(blz, account)
	}
	def account(int blz, long account){
		new LegacyAccount(blz, account)
	}

	fact account(null,null) should throw NumberFormatException
	fact account("1",null) should throw NumberFormatException
	fact account(null,"1") should throw NumberFormatException
	fact account("1","1") should throw IllegalArgumentException
	fact account("12345678","0") should throw IllegalArgumentException
	fact account("12345678","12345678901") should throw IllegalArgumentException

	fact account("12345678","1").bankCode should be 12345678
	fact account("87654321","1").bankCode should be 87654321

	fact account("12345678","1").account should be 1L
	fact account("12345678","1").accountLength should be 1
	fact account("12345678","1").paddedAccount should be "0000000001"

	fact account(12345678,2).bankCode should be 12345678
	fact account(87654321,2).bankCode should be 87654321

	fact account(12345678,2).account should be 2L
	fact account(12345678,2).accountLength should be 1
	fact account(12345678,2).paddedAccount should be "0000000002"
}