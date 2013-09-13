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

import static extension java.lang.Long.*
import static extension java.lang.Integer.*

import java.util.Map
import org.joda.time.LocalDate
import org.avacodo.model.BankConfig

package class Rule005200 extends Rule {
	
	val package static final Map<Pair<Integer,Long>,Long> special=newImmutableMap(
			'''
		67220020 5308810004 0002662604
		67220020 5308810000 0002659600
		67020020 5203145700 7496510994
		69421020 6208908100 7481501341
		66620020 4840404000 7498502663
		64120030 1201200100 7477501214
		64020030 1408050100 7469534505
		63020130 1112156300 0004475655
		62030050 7002703200 7406501175
		69220020 6402145400 7485500252
	'''.toString.split("\n").map[
			val sp=split(" ")
			(sp.get(0).parseInt->sp.get(1).parseLong)->sp.get(2).parseLong
		]
	)
	
	override applyTo(RichIbanResult it, BankConfig config, LocalDate date) {
		val replace=special.get(account.bankCode->account.account)
		if(replace===null){
			creationNotPossible("all accounts with few explicit exceptions are excluded from iban creation")
		} else {
			overrideAccount(replace)
			overrideBankCode(60050101)
			// skipping account validation
			suppressValidation(true)
			defaultApply(config.accountCheckMethod, date)
		}
	}
}