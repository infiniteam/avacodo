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
import org.avacodo.model.BankConfig
import org.joda.time.LocalDate

package class Rule005300 extends Rule {
	
	val package static final Map<Pair<Integer,Long>,Long> special=newImmutableMap(
	'''
		55050000 35000 7401555913
		55050000 119345106 7401555906
		55050000 908 7401507480
		55050000 901 7401507497
		55050000 910 7401507466
		55050000 35100 7401555913
		55050000 902 7401507473
		55050000 44000 7401555872
		55050000 110132511 7401550530
		55050000 110024270 7401501266
		55050000 3500 7401555913
		55050000 110050002 7401502234
		55050000 55020100 7401555872
		55050000 110149226 7401512248
		60020030 1047444300 7871538395
		60020030 1040748400 0001366705
		60020030 1000617900 0002009906
		60020030 1003340500 0002001155
		60020030 1002999900 0002588991
		60020030 1004184600 7871513509
		60020030 1000919900 7871531505
		60020030 1054290000 7871521216
		60050000 1523 0001364934
		60050000 2811 0001367450
		60050000 2502 0001366705
		60050000 250412 7402051588
		60050000 3009 0001367924
		60050000 4596 0001372809
		60050000 3080 0002009906
		60050000 1029204 0002782254
		60050000 3002 0001367924
		60050000 123456 0001362826
		60050000 2535 0001119897
		60050000 5500 0001375703
		66020020 4002401000 7495500967
		66020020 4000604100 0002810030
		66020020 4002015800 7495530102
		66020020 4003746700 7495501485
		66050000 86567 0001364934
		66050000 86345 7402046641
		66050000 85304 7402045439
		66050000 85990 7402051588
		86050000 1016 7461500128
		86050000 3535 7461505611
		86050000 2020 7461500018
		86050000 4394 7461505714
	'''.toString.split(NL).map[
			val sp=split(" ")
			(sp.get(0).parseInt->sp.get(1).parseLong)->sp.get(2).parseLong
		]
	)
	
	override package final applyTo(RichIbanResult it, BankConfig config, LocalDate date) {
		val replace=special.get(account.bankCode->account.account)
		if(replace!==null){
			overrideAccount(replace)
			overrideBankCode(60050101)
			suppressValidation(true)
		}
		defaultApply(config.accountCheckMethod, date)
	}
}