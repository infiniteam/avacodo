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

import java.util.Map

package class Rule001700 extends ReplaceRule {

	private static final Map<Long,Long> replace='''
		100 2009090013
		111 2111111017
		240 2100240010
		4004 2204004016
		4444 2044444014
		6060 2016060014
		102030 1102030016
		333333 2033333016
		909090 2009090013
		50005000 5000500013	
	'''.toAccountAccountMap

	override replace(RichIbanResult it) {
		defaultAccountReplace(replace)
	}
}