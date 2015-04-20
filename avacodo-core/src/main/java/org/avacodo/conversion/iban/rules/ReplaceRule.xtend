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

import org.joda.time.LocalDate
import org.avacodo.model.BankConfig
import java.util.Map

abstract package class ReplaceRule extends Rule {

	def void replace(RichIbanResult account)

	override package final applyTo(RichIbanResult result, BankConfig config, LocalDate date) {
		result.replace
		defaultApply(result, config.accountCheckMethod, date)
		result
	}

	def protected static final toAccountAccountMap(CharSequence s){
		newImmutableMap(s.toString.trim.split(NL).map[
			val sp=it.split(" ")
			Long::parseLong(sp.get(0).trim)->Long::parseLong(sp.get(1).trim)
		])
	}

	def protected defaultAccountReplace(RichIbanResult it, Map<Long,Long> replaceMap){
		val replaceAc=replaceMap.get(account.account)
		if(replaceAc!==null){
			overrideAccount(replaceAc)
		}
	}
}
