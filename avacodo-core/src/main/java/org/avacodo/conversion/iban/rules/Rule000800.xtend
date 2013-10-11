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

import java.util.Set
import org.joda.time.LocalDate
import org.avacodo.model.BankConfig

package class Rule000800 extends Rule {

	private static final Set<Integer> replaceBlz=
	"10020200; 20120200; 25020200; 30020500; 51020000; 55020000; 60120200; 70220200; 86020200"
	.split(";").map[Integer::parseInt(it.trim)].toSet

	override applyTo(RichIbanResult it, BankConfig config, LocalDate date) {
		validateWith(validator,"60",date)
		if(replaceBlz.contains(account.bankCode)){
			overrideBankCode(50020200)
		}
		overrideBic("BHFBDEFF500")
		defaultApply(config.accountCheckMethod,date)
		it
	}
}
