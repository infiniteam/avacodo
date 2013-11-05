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
import org.avacodo.model.LegacyAccount
import org.avacodo.model.DeIban
import org.avacodo.model.BankConfig
import org.joda.time.LocalDate

class Rule005600 extends Rule {

	private static final Map<Long, LegacyAccount> donationMap = '''
		36 1010240003 DE29380101111010240003
		50 1328506100 DE55480101111328506100
		99 1826063000 DE26430101111826063000
		110 1015597802 DE52250101111015597802
		240 1010240000 DE13380101111010240000
		333 1011296100 DE15380101111011296100
		555 1600220800 DE54100101111600220800
		556 1000556100 DE42390101111000556100
		606 1967153801 DE70250101111967153801
		700 1070088000 DE92265101111070088000
		777 1006015200 DE72250101111006015200
		999 1010240001 DE83380101111010240001
		1234 1369152400 DE91250101111369152400
		1313 1017500000 DE48570101111017500000
		1888 1241113000 DE81370101111241113000
		1953 1026500901 DE30250101111026500901
		1998 1547620500 DE47670101111547620500
		2007 1026500907 DE62250101111026500907
		4004 1635100100 DE45370101111635100100
		4444 1304610900 DE88670101111304610900
		5000 1395676000 DE20250101111395676000
		5510 1611754300 DE96290101111611754300
		6060 1000400200 DE43500101111000400200
		6800 1296401301 DE02670101111296401301
		55555 1027758200 DE13380101111027758200
		60000 1005007001 DE98500101111005007001
		66666 1299807801 DE10200101111299807801
		102030 1837501600 DE59370101111837501600
		121212 1249461502 DE48700101111249461502
		130500 1413482100 DE78300101111413482100
		202020 1213431002 DE24370101111213431002
		414141 1010555101 DE59380101111010555101
		666666 1798758900 DE49200101111798758900
		5000000 1403124100 DE62370101111403124100
		500500500 1045720000 DE17600101111045720000
	'''.toString.trim.toMap

	private static def toMap(String s) {
		newHashMap(
			s.split(NL).map [
				val sp = it.split(" ")
				Long::parseLong(sp.get(0).trim) -> DeIban.accountFromIban(sp.get(2).trim)
			])
	}

	override applyTo(RichIbanResult it, BankConfig config, LocalDate date) {
		val replaceAc = donationMap.get(account.account)
		if (replaceAc !== null) {
			overrideAccount(replaceAc.account)
			overrideBankCode(replaceAc.bankCode)

		} else if (account.accountLength != 10) {
			creationNotPossible("account length 10 is required for this rule")
		}
		defaultApply(it, config.accountCheckMethod, date)
	}
}
