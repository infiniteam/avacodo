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

package class Rule002900 extends ReplaceRule {

	override replace(RichIbanResult it) {
		if(!charAtEqual(0,'0') && charAtEqual(3,'0')){
			val ac=account.paddedAccount
			overrideAccount(ac.deletePosition4)
		}
	}

	def package deletePosition4(String it){
		Long::parseLong('''«substring(0,3)»«substring(4,10)»''')
	}
}
