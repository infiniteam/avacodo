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

import org.avacodo.conversion.iban.rules.ReplaceRule
import java.util.Map

package class Rule001800 extends ReplaceRule {

	private static final Map<Long,Long> replace='''
		556 120440110
		5435435430 543543543
		2157 121787016
		9800 120800019
		202050 1221864014
	'''.toAccountAccountMap

	override replace(RichIbanResult it) {
		if(account.bankCode==390_601_80){
			defaultAccountReplace(replace)
		}
	}
}