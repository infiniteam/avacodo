/*
 * #%L
 * Avacodo
 * %%
 * Copyright (C) 2013 infiniteam
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

import java.util.Map

package class Rule000400 extends ReplaceRule {

	private static final Map<Long,Long> replace=
	'''
		135 0990021440
		1111 6600012020
		1900 0920019005
		7878 0780008006
		8888 0250030942
		9595 1653524703
		97097 0013044150
		112233 0630025819
		336666 6604058903
		484848 0920018963
	'''.toAccountAccountMap
	
	override replace(RichIbanResult it) {
		defaultAccountReplace(replace)
	}
}
