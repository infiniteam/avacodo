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

package class Rule000900 extends ReplaceRule {

	override replace(RichIbanResult it) {
		if(account.bankCode==68351976){
			val padded=account.paddedAccount
			if(padded.startsWith("1116")){
				val repl=padded.replaceFirst("1116","3047")
				overrideAccount(Long::parseLong(repl))
			}
		}
		overrideBankCode(68351557)
		overrideBic("SOLADES1SFH")
	}
}
