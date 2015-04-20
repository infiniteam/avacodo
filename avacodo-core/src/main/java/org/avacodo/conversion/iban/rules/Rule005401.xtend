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

import java.util.Set
import org.joda.time.LocalDate
import org.avacodo.model.BankConfig

/**
 * IBAN rule for Evangelische Darlehnsgenossenschaft eG, valid from March 3, 2014
 */
package class Rule005401 extends Rule005400 {

	private static final Set<Long> excludeValidation='''
		624044
		4063060
		20111908
		20211908
		20311908
		20411908
		20511908
		20611908
		20711908
		20811908
		20911908
		21111908
		21211908
		21311908
		21411908
		21511908
		21611908
		21711908
		21811908
		21911908
		22111908
		22211908
		22311908
		22411908
		22511908
		22611908
		46211991
		50111908
		50211908
		50311908
		50411908
		50511908
		50611908
		50711908
		50811908
		50911908
		51111908
		51111991
		51211908
		51211991
		51311908
		51411908
		51511908
		51611908
		51711908
		51811908
		51911908
		52111908
		52111991
		52211908
		52211991
		52311908
		52411908
		52511908
		52611908
		52711908
		52811908
		52911908
		53111908
		53211908
		53311908
		57111908
		58111908
		58211908
		58311908
		58411908
		58511908
		80111908
		80211908
		80311908
		80411908
		80511908
		80611908
		80711908
		80811908
		80911908
		81111908
		81211908
		81311908
		81411908
		81511908
		81611908
		81711908
		81811908
		81911908
		82111908
		82211908
		82311908
		82411908
		82511908
		82611908
		82711908
		82811908
		82911908
		99624044
		300143869
	'''.asLongSet
	
	def static private asLongSet(CharSequence sequence) {
		sequence.toString.split(NL).map[Long::parseLong(it.trim)].toSet		
	}

	override applyTo(RichIbanResult it, BankConfig config, LocalDate date) {
		replace
		if(excludeValidation.contains(account.account)) {
			suppressValidation(true)
			overrideBankCode(21060237)
		}
		defaultApply(it, config.accountCheckMethod, date)
	}

}
