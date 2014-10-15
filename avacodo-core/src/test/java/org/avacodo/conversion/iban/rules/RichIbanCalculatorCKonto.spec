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
import static extension org.avacodo.conversion.iban.rules.DoubleChecker.*

//pit falls from http://www.ckonto.de/
describe RuleBasedIbanCalculator "cKonto"{

	static RuleBasedIbanCalculator subject=DefaultCalculator.INSTANCE

	fact iban(10050000,484848) should be "DE43100500000920018963"
	fact bic(10050000,484848) should be "BELADEBEXXX"
	fact iban(70150000,34343434) should be "DE30701500001000506517"
	fact bic(70150000,34343434) should be "SSKMDEMMXXX"
	fact iban(37050198,15000023) should be "DE98370501980015002223"
	fact bic(37050198,15000023) should be "COLSDE33XXX"
	fact iban(68351976,1116232594) should be "DE03683515573047232594"
	fact bic(68351976,1116232594) should be "SOLADES1SFH"
	fact iban(50050201,800000) should be "DE89500502010000180802"
	fact bic(50050201,800000) should be "HELADEF1822"
	fact iban(32050000,47800) should be "DE36320500000000047803"
	fact bic(32050000,47800) should be "SPKRDE33XXX"
	fact iban(50850049,5000002096L) should be "DE95500500005000002096"
	fact bic(50850049,5000002096L) should be "HELADEFFXXX"
	fact iban(40050000,60624) should be "DE15300500000000060624"
	fact bic(40050000,60624) should be "WELADEDDXXX"
	fact iban(33060616,12345671) should be "DE09300606010012345671"
	fact bic(33060616,12345671) should be "DAAEDEDDXXX"
	fact iban(37060193,2120041) should be "DE92370601930002130041"
	fact bic(37060193,2120041) should be "GENODED1PAX"
	fact iban(37160087,300000) should be "DE68371600870018128012"
	fact bic(37160087,300000) should be "GENODED1CGN"
	fact iban(38060186,50005000) should be "DE95380601865000500013"
	fact bic(38060186,50005000) should be "GENODED1BRS"
	fact iban(39060180,202050) should be "DE61390601801221864014"
	fact bic(39060180,202050) should be "GENODED1AAC"
	fact iban(50130100,20475000) should be "DE82501203830020475000"
	fact bic(50130100,20475000) should be "DELBDE33XXX"
	fact iban(36220030,900826) should be "DE03360200300000900826"
	fact bic(36220030,900826) should be "NBAGDE3EXXX"
	fact iban(43060967,1111111) should be "DE22430609672222200000"
	fact bic(43060967,1111111) should be "GENODEM1GLS"
	fact iban(26590025,700) should be "DE76265900251000700800"
	fact bic(26590025,700) should be "GENODEF1OSV"
	fact iban(64150182,2777939) should be "DE81600501010002777939"
	fact bic(64150182,2777939) should be "SOLADEST600"
	fact iban(35060190,55111) should be "DE21350601900000055111"
	fact bic(35060190,55111) should be "GENODED1DKD"
	fact iban(32060362,4444) should be "DE23320603620000004444"
	fact bic(32060362,4444) should be "GENODED1HTK"
	fact iban(25050299,17095) should be "DE69250501800000017095"
	fact bic(25050299,17095) should be "SPKHDE2HXXX"
	fact iban(51210800,2600123456L) should be "DE02512108000260123456"
	fact bic(51210800,2600123456L) should be "SOGEDEFFXXX"
	fact iban(13091054,9370620080L) should be "DE07130910549370620080"
	fact bic(13091054,9370620080L) should be "GENODEF1HST"
//regular test case
//	fact iban(54520071,6790149813L) should be "DE77545201946790149813"
//	fact bic(54520071,6790149813L) should be "HYVEDEMM483"
	fact iban(20050000,101105) should be "DE32210500000101105000"
	fact bic(20050000,101105) should be "HSHNDEHHXXX"
	fact iban(20110700,123456) should be "DE41300107000000123456"
	fact bic(20110700,123456) should be "BOTKDEDXXXX"
	fact iban(25621327,1234567490) should be "DE46280200501234567490"
	fact bic(25621327,1234567490) should be "OLBODEH2XXX"	
	fact iban(62220000,1234567890) should be "DE96500604000000011404"
	fact bic(62220000,1234567890) should be "GENODEFFXXX"
	fact iban(60651070,868) should be "DE49666500850000000868"
	fact bic(60651070,868) should be "PZHSDE66XXX"
	fact iban(68050101,202) should be "DE51680501010002282022"
	fact bic(68050101,202) should be "FRSPDE66XXX"
	fact iban(25020600,1234567897) should be "DE67310108331234567897"
	fact bic(25020600,1234567897) should be "SCFBDE33XXX"
	fact iban(20133300,12345678) should be "DE63201333001234567800"
	fact bic(20133300,12345678) should be "SCFBDE33XXX"

	def iban(int blz, long account){
		subject.getIban(new LegacyAccount(blz, account)).doubleCheck.iban
	}
	def bic(int blz, long account){
		subject.getIban(new LegacyAccount(blz, account)).bic
	}
}
