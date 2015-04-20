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

import org.avacodo.model.LegacyAccount
import org.avacodo.validation.account.AccountValidationException

import static org.junit.Assert.*

import static extension org.avacodo.conversion.iban.rules.CheckMethodConfig.*
import static extension org.avacodo.conversion.iban.rules.DoubleChecker.*

describe Rule005401 {
	before {
		subject=new Rule005401
		subject.setValidator(new FailingValidator)
	}

	// account numbers with check digit error which are allowed for IBAN calculation
	fact ibanDoubleCheck(10060237,624044) should match "DE96 2106 0237 0000 6240 44"
	fact ibanDoubleCheck(10060237,4063060) should match "DE11 2106 0237 0004 0630 60"
	fact ibanDoubleCheck(10060237,20111908) should match "DE97 2106 0237 0020 1119 08"
	fact ibanDoubleCheck(10060237,20211908) should match "DE92 2106 0237 0020 2119 08"
	fact ibanDoubleCheck(10060237,20311908) should match "DE87 2106 0237 0020 3119 08"
	fact ibanDoubleCheck(10060237,20411908) should match "DE82 2106 0237 0020 4119 08"
	fact ibanDoubleCheck(10060237,20511908) should match "DE77 2106 0237 0020 5119 08"
	fact ibanDoubleCheck(10060237,20611908) should match "DE72 2106 0237 0020 6119 08"
	fact ibanDoubleCheck(10060237,20711908) should match "DE67 2106 0237 0020 7119 08"
	fact ibanDoubleCheck(10060237,20811908) should match "DE62 2106 0237 0020 8119 08"
	fact ibanDoubleCheck(10060237,20911908) should match "DE57 2106 0237 0020 9119 08"
	fact ibanDoubleCheck(10060237,21111908) should match "DE47 2106 0237 0021 1119 08"
	fact ibanDoubleCheck(10060237,21211908) should match "DE42 2106 0237 0021 2119 08"
	fact ibanDoubleCheck(10060237,21311908) should match "DE37 2106 0237 0021 3119 08"
	fact ibanDoubleCheck(10060237,21411908) should match "DE32 2106 0237 0021 4119 08"
	fact ibanDoubleCheck(10060237,21511908) should match "DE27 2106 0237 0021 5119 08"
	fact ibanDoubleCheck(10060237,21611908) should match "DE22 2106 0237 0021 6119 08"
	fact ibanDoubleCheck(10060237,21711908) should match "DE17 2106 0237 0021 7119 08"
	fact ibanDoubleCheck(10060237,21811908) should match "DE12 2106 0237 0021 8119 08"
	fact ibanDoubleCheck(10060237,21911908) should match "DE07 2106 0237 0021 9119 08"
	fact ibanDoubleCheck(10060237,22111908) should match "DE94 2106 0237 0022 1119 08"
	fact ibanDoubleCheck(10060237,22211908) should match "DE89 2106 0237 0022 2119 08"
	fact ibanDoubleCheck(10060237,22311908) should match "DE84 2106 0237 0022 3119 08"
	fact ibanDoubleCheck(10060237,22411908) should match "DE79 2106 0237 0022 4119 08"
	fact ibanDoubleCheck(10060237,22511908) should match "DE74 2106 0237 0022 5119 08"
	fact ibanDoubleCheck(10060237,22611908) should match "DE69 2106 0237 0022 6119 08"
	fact ibanDoubleCheck(10060237,46211991) should match "DE43 2106 0237 0046 2119 91"
	fact ibanDoubleCheck(10060237,50111908) should match "DE52 2106 0237 0050 1119 08"
	fact ibanDoubleCheck(10060237,50211908) should match "DE47 2106 0237 0050 2119 08"
	fact ibanDoubleCheck(10060237,50311908) should match "DE42 2106 0237 0050 3119 08"
	fact ibanDoubleCheck(10060237,50411908) should match "DE37 2106 0237 0050 4119 08"
	fact ibanDoubleCheck(10060237,50511908) should match "DE32 2106 0237 0050 5119 08"
	fact ibanDoubleCheck(10060237,50611908) should match "DE27 2106 0237 0050 6119 08"
	fact ibanDoubleCheck(10060237,50711908) should match "DE22 2106 0237 0050 7119 08"
	fact ibanDoubleCheck(10060237,50811908) should match "DE17 2106 0237 0050 8119 08"
	fact ibanDoubleCheck(10060237,50911908) should match "DE12 2106 0237 0050 9119 08"
	fact ibanDoubleCheck(10060237,51111908) should match "DE02 2106 0237 0051 1119 08"
	fact ibanDoubleCheck(10060237,51111991) should match "DE89 2106 0237 0051 1119 91"
	fact ibanDoubleCheck(10060237,51211908) should match "DE94 2106 0237 0051 2119 08"
	fact ibanDoubleCheck(10060237,51211991) should match "DE84 2106 0237 0051 2119 91"
	fact ibanDoubleCheck(10060237,51311908) should match "DE89 2106 0237 0051 3119 08"
	fact ibanDoubleCheck(10060237,51411908) should match "DE84 2106 0237 0051 4119 08"
	fact ibanDoubleCheck(10060237,51511908) should match "DE79 2106 0237 0051 5119 08"
	fact ibanDoubleCheck(10060237,51611908) should match "DE74 2106 0237 0051 6119 08"
	fact ibanDoubleCheck(10060237,51711908) should match "DE69 2106 0237 0051 7119 08"
	fact ibanDoubleCheck(10060237,51811908) should match "DE64 2106 0237 0051 8119 08"
	fact ibanDoubleCheck(10060237,51911908) should match "DE59 2106 0237 0051 9119 08"
	fact ibanDoubleCheck(10060237,52111908) should match "DE49 2106 0237 0052 1119 08"
	fact ibanDoubleCheck(10060237,52111991) should match "DE39 2106 0237 0052 1119 91"
	fact ibanDoubleCheck(10060237,52211908) should match "DE44 2106 0237 0052 2119 08"
	fact ibanDoubleCheck(10060237,52211991) should match "DE34 2106 0237 0052 2119 91"
	fact ibanDoubleCheck(10060237,52311908) should match "DE39 2106 0237 0052 3119 08"
	fact ibanDoubleCheck(10060237,52411908) should match "DE34 2106 0237 0052 4119 08"
	fact ibanDoubleCheck(10060237,52511908) should match "DE29 2106 0237 0052 5119 08"
	fact ibanDoubleCheck(10060237,52611908) should match "DE24 2106 0237 0052 6119 08"
	fact ibanDoubleCheck(10060237,52711908) should match "DE19 2106 0237 0052 7119 08"
	fact ibanDoubleCheck(10060237,52811908) should match "DE14 2106 0237 0052 8119 08"
	fact ibanDoubleCheck(10060237,52911908) should match "DE09 2106 0237 0052 9119 08"
	fact ibanDoubleCheck(10060237,53111908) should match "DE96 2106 0237 0053 1119 08"
	fact ibanDoubleCheck(10060237,53211908) should match "DE91 2106 0237 0053 2119 08"
	fact ibanDoubleCheck(10060237,53311908) should match "DE86 2106 0237 0053 3119 08"
	fact ibanDoubleCheck(10060237,57111908) should match "DE90 2106 0237 0057 1119 08"
	fact ibanDoubleCheck(10060237,58111908) should match "DE40 2106 0237 0058 1119 08"
	fact ibanDoubleCheck(10060237,58211908) should match "DE35 2106 0237 0058 2119 08"
	fact ibanDoubleCheck(10060237,58311908) should match "DE30 2106 0237 0058 3119 08"
	fact ibanDoubleCheck(10060237,58411908) should match "DE25 2106 0237 0058 4119 08"
	fact ibanDoubleCheck(10060237,58511908) should match "DE20 2106 0237 0058 5119 08"
	fact ibanDoubleCheck(10060237,80111908) should match "DE07 2106 0237 0080 1119 08"
	fact ibanDoubleCheck(10060237,80211908) should match "DE02 2106 0237 0080 2119 08"
	fact ibanDoubleCheck(21060237,80311908) should match "DE94 2106 0237 0080 3119 08"
	fact ibanDoubleCheck(10060237,80411908) should match "DE89 2106 0237 0080 4119 08"
	fact ibanDoubleCheck(10060237,80511908) should match "DE84 2106 0237 0080 5119 08"
	fact ibanDoubleCheck(10060237,80611908) should match "DE79 2106 0237 0080 6119 08"
	fact ibanDoubleCheck(10060237,80711908) should match "DE74 2106 0237 0080 7119 08"
	fact ibanDoubleCheck(10060237,80811908) should match "DE69 2106 0237 0080 8119 08"
	fact ibanDoubleCheck(10060237,80911908) should match "DE64 2106 0237 0080 9119 08"
	fact ibanDoubleCheck(10060237,81111908) should match "DE54 2106 0237 0081 1119 08"
	fact ibanDoubleCheck(10060237,81211908) should match "DE49 2106 0237 0081 2119 08"
	fact ibanDoubleCheck(10060237,81311908) should match "DE44 2106 0237 0081 3119 08"
	fact ibanDoubleCheck(10060237,81411908) should match "DE39 2106 0237 0081 4119 08"
	fact ibanDoubleCheck(10060237,81511908) should match "DE34 2106 0237 0081 5119 08"
	fact ibanDoubleCheck(10060237,81611908) should match "DE29 2106 0237 0081 6119 08"
	fact ibanDoubleCheck(10060237,81711908) should match "DE24 2106 0237 0081 7119 08"
	fact ibanDoubleCheck(10060237,81811908) should match "DE19 2106 0237 0081 8119 08"
	fact ibanDoubleCheck(10060237,81911908) should match "DE14 2106 0237 0081 9119 08"
	fact ibanDoubleCheck(21060237,82111908) should match "DE04 2106 0237 0082 1119 08"
	fact ibanDoubleCheck(10060237,82211908) should match "DE96 2106 0237 0082 2119 08"
	fact ibanDoubleCheck(10060237,82311908) should match "DE91 2106 0237 0082 3119 08"
	fact ibanDoubleCheck(10060237,82411908) should match "DE86 2106 0237 0082 4119 08"
	fact ibanDoubleCheck(10060237,82511908) should match "DE81 2106 0237 0082 5119 08"
	fact ibanDoubleCheck(21060237,82611908) should match "DE76 2106 0237 0082 6119 08"
	fact ibanDoubleCheck(10060237,82711908) should match "DE71 2106 0237 0082 7119 08"
	fact ibanDoubleCheck(10060237,82811908) should match "DE66 2106 0237 0082 8119 08"
	fact ibanDoubleCheck(10060237,82911908) should match "DE61 2106 0237 0082 9119 08"
	fact ibanDoubleCheck(10060237,99624044) should match "DE93 2106 0237 0099 6240 44"
	fact ibanDoubleCheck(10060237,300143869) should match "DE30 2106 0237 0300 1438 69"

	def ibanDoubleCheck(int blz,long konto) {
		try{
			subject.applyTo(new LegacyAccount(blz, konto),"XX".config).iban
		} catch(AccountValidationException e){
			fail("account validation should match suppressed " + konto)
		}
	}
	
	def boolean should_match(String a, String b){
		a should be b.replaceAll(" ","")
	}

}
