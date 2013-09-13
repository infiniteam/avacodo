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

package class Rule005100 extends ReplaceRule {

	val private static final Map<Long,Long> replaceMap=newHashMap(
		333L->7832500881L,
		502L->1108884L,
		500500500L->5005000L,
		502502502L->1108884L
	)

	override replace(RichIbanResult it) {
		val replace=replaceMap.get(account.account)
		if(replace!==null){
			overrideAccount(replace)
		}
	}

}