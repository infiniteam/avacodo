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

import org.avacodo.model.LegacyAccount

import static extension org.avacodo.conversion.iban.rules.CheckMethodConfig.*

/**TODO!!! */
describe Rule {

	before{
		subject=new TestRule
	}

	fact subject.charEqual('a','a') should be true
	fact subject.charEqual('0','0') should be true
	fact subject.charEqual('1','1') should be true
	fact subject.charEqual('3','3') should be true
	fact subject.charEqual('6','6') should be true
	fact subject.charEqual('9','9') should be true

	fact subject.charEqual('1','0') should be false
	fact subject.charEqual('a','A') should be false

	fact charAt(1234567890,0,'1') should be true
	fact charAt(1234567890,1,'2') should be true
	fact charAt(1234567890,2,'3') should be true
	fact charAt(1234567890,3,'4') should be true
	fact charAt(1234567890,4,'5') should be true
	fact charAt(1234567890,5,'6') should be true
	fact charAt(1234567890,6,'7') should be true
	fact charAt(1234567890,7,'8') should be true
	fact charAt(1234567890,8,'9') should be true
	fact charAt(1234567890,9,'0') should be true

	fact charAt(123,7,'1') should be true
	fact charAt(123,8,'2') should be true
	fact charAt(123,9,'3') should be true

	fact "validatorGetter"{
		subject.validator should not be null
		val newValidator=new FailingValidator
		subject.validator=newValidator
		subject.validator===newValidator should be true
	}

	def charAt(long account, int pos, char c){
		subject.charAtEqual(new RichIbanResult(new LegacyAccount(12345678, account),"09".config), pos, c)
	}
}