/*
 * #%L
 * Avacodo
 * %%
 * Copyright (C) 2013 - 2014 infiniteam
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
import org.avacodo.model.UnknownBankCodeException
import org.avacodo.validation.account.AccountValidationException

import static extension org.avacodo.conversion.iban.rules.DoubleChecker.*

/**
 * if not stated otherwise test cases here are checked against http://www.ckonto.de/
 * and http://www.sparkasse.de/privatkunden/konto-karte/iban-rechner.html
 */
describe RuleBasedIbanCalculator "Others"{

	static RuleBasedIbanCalculator subject=DefaultCalculator.INSTANCE

	//unknown bank code
	fact iban(999_999_99,1) should throw UnknownBankCodeException

	//Rule 5 border line case 
	//base account number is too short (5, 6, 7 digits)
	//other converters accept the account and give an iban
	fact iban(700_800_56, 23) should throw AccountValidationException

	//Rule 12 
	fact iban(508_500_49,5000002096L) should be "DE95500500005000002096"
	fact bic(508_500_49,5000002096L) should be "HELADEFFXXX"

	//Rule 13 
	fact iban(400_500_00,60624) should be "DE15300500000000060624"
	fact bic(400_500_00,60624) should be "WELADEDDXXX"
	fact iban(440_500_00,60624) should be "DE15300500000000060624"
	fact bic(440_500_00,60624) should be "WELADEDDXXX"

	//Rule 20
	fact iban(500_700_10, 9999) should be "DE80500700100092777202"
	fact bic(500_700_10,9999) should be "DEUTDEFFXXX"
	fact iban(700_700_10, 3500022) should be "DE86700700100350002200"
	fact bic(700_700_10,3500022) should be "DEUTDEMMXXX"

	fact iban(50070010,5719083) should endWith "0571908300"
	fact bic(50070010,5719083) should be "DEUTDEFFXXX"

//with version 1 this ambiguity was removed
//	fact "ambiguous rule 20"{
//		try{
//			iban(50070010,5719083)
//			fail
//		}catch(IbanAmbiguousException e){
//			e.iban.iban should endWith "0005719083"
//			e.iban2.iban should endWith "0571908300"
//			e.iban2.bic should be "DEUTDEFFXXX"
//		}
//	}

	//Rule 25 
	fact iban(614_501_91,2777939) should be "DE81600501010002777939"
	fact bic(614_501_91,2777939) should be "SOLADEST600"
	fact iban(622_501_82,2777939) should be "DE81600501010002777939"
	fact bic(622_501_82,2777939) should be "SOLADEST600"
	fact iban(611_501_85,2777939) should be "DE81600501010002777939"
	fact bic(611_501_85,2777939) should be "SOLADEST600"

	//Rule 36
	fact iban(200_500_00,101105) should be "DE32210500000101105000"
	fact bic(200_500_00,101105) should be "HSHNDEHHXXX"

	//Rule 39
	fact iban(280_233_25,4228) should be "DE80280200500000004228"
	fact bic(280_233_25,4228) should be "OLBODEH2XXX"

	//Rule 41
	//Sparkasse does not process bankcode 62220000

	//Rule 42
	fact iban(430_000_00,12305678) should be "DE46430000000012305678"
	fact bic(430_000_00,12305678) should be "MARKDEF1430"

	//Rule 46
	fact iban(250_206_00,1115) should be "DE81310108330000001115"
	fact bic(250_206_00,1115) should be "SCFBDE33XXX"

	def iban(int blz, long account){
		subject.getIban(new LegacyAccount(blz, account)).doubleCheck.iban
	}
	def bic(int blz, long account){
		subject.getIban(new LegacyAccount(blz, account)).bic
	}

}
