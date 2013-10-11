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
import org.avacodo.model.BankConfig
import org.joda.time.LocalDate

package class Rule004900 extends Rule {

	val private static final Map<Long, Long> replaceMap = newHashMap(
		36L -> 2310113L,
		936L -> 2310113L,
		999L -> 1310113L,
		6060L -> 160602L
	)

	override applyTo(RichIbanResult it, BankConfig config, LocalDate date) {
		val replace = replaceMap.get(account.account)
		if (replace !== null) {
			overrideAccount(replace)
			suppressValidation(true)
		} else if(charAtEqual(4, '9')){
			/*
			 * "Für Kontonummern mit einer '9' an der 5. Stelle muss die Kontonummer, auf deren
			 * Basis die IBAN ermittelt wird, abweichend berechnet werden. Die ersten 4 Stellen
			 * (inkl. aufgefüllter Nullen) müssen ans Ende gestellt werden, so dass die Kontonummer
			 * dann immer mit der '9' anfängt."
			 */
			overrideAccount(moveFirst4ToEnd(account.account))
			suppressValidation(true)
		}
		defaultApply(config.accountCheckMethod, date)
	}

	def package moveFirst4ToEnd(long account){
		val last6 = account % 1000000;
		last6 * 10000 + account / 1000000
	}
}
