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
