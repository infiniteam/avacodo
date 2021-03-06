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

import java.util.Map
import org.avacodo.model.BankConfig
import org.joda.time.LocalDate

package class Rule005400 extends Rule {
	
	private static final Map<Long,Long> replaceMap=ReplaceRule.toAccountAccountMap("
		500 500500
		502 502502
		18067 180670
		484848 484849
		636306 63606
		760440 160440
		1018413 10108413
		2601577 26015776
		5005000 500500
		10796740 10796743
		11796740 11796743
		12796740 12796743
		13796740 13796743
		14796740 14796743
		15796740 15796743
		16307000 163107000
		16610700 166107000
		16796740 16796743
		17796740 17796743
		18796740 18796743
		19796740 19796743
		20796740 20796743
		21796740 21796743
		22796740 22796743
		23796740 23796743
		24796740 24796743
		25796740 25796743
		26610700 266107000
		26796740 26796743
		27796740 27796743
		28796740 28796743
		29796740 29796743
		45796740 45796743
		50796740 50796743
		51796740 51796743
		52796740 52796743
		53796740 53796743
		54796740 54796743
		55796740 55796743
		56796740 56796743
		57796740 57796743
		58796740 58796743
		59796740 59796743
		60796740 60796743
		61796740 61796743
		62796740 62796743
		63796740 63796743
		64796740 64796743
		65796740 65796743
		66796740 66796743
		67796740 67796743
		68796740 68796743
		69796740 69796743
		1761070000 176107000
		2210531180 201053180");

    def protected replace(RichIbanResult it) {
		val replaceAc = replaceMap.get(account.account)
		if (replaceAc !== null) {
			overrideAccount(replaceAc)
		}
	}

	override applyTo(RichIbanResult it, BankConfig config, LocalDate date) {
		replace
		defaultApply(it, config.accountCheckMethod, date)
	}

}
