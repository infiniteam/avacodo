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

package class Rule003300 extends Rule {

	val private static final Map<Long,Long> replaceMap=newHashMap(
		22222L->5803435253L,
		1111111L->39908140L,
		94L->2711931L,
		7777777L->5800522694L,
		55555L->5801800000L
	)
	override package applyTo(RichIbanResult it, BankConfig config, LocalDate date) {
		if(account.bankCode==70020270 && !config.deletionAnnounced){
			val replace=replaceMap.get(account.account)
			if(replace!==null){
				overrideAccount(replace)
			}
			if(account.accountLength==10){
				HypoReplacer::overrideByAccount(it)
				defaultApply(config.accountCheckMethod,date)
			} else{
				defaultApply(config.accountCheckMethod,date)
			}
		}else{	
			creationNotPossible("bank code must be 700_202_70 and not be announced for deletion "+account.bankCode)
		}
	}
}
