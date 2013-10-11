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

import org.avacodo.conversion.iban.IbanCreationImpossibleException
import org.avacodo.model.LegacyAccount
import org.avacodo.validation.account.AccountValidationException

import static extension org.avacodo.conversion.iban.rules.DoubleChecker.*

describe RuleBasedIbanCalculator "IbanRuleSpec"{

	static RuleBasedIbanCalculator subject=DefaultCalculator.INSTANCE

	//Rule 5 (tests from spec including bic)
	fact iban(26580070, 732502200) should be "DE32265800700732502200"
	fact bic(26580070, 732502200) should be "DRESDEFF265"
	
	fact iban(26580070, 7325022) should be "DE32265800700732502200"
	fact bic(26580070, 7325022) should be "DRESDEFF265"

	fact iban(26580070, 8732502200L) should be "DE60265800708732502200"
	fact bic(26580070, 8732502200L) should be "DRESDEFF265"

	fact iban(26580070, 4820379900L) should be "DE37265800704820379900"
	fact bic(26580070, 4820379900L) should be "DRESDEFF265"

	fact iban(50080000, 1814706100L) should throw AccountValidationException
	fact iban(50080000, 2814706100L) should throw AccountValidationException
	fact iban(50080000, 3814706100L) should throw AccountValidationException

	fact iban(50080000, 4814706100L) should be "DE70500800004814706100"
	fact bic(50080000, 4814706100L) should be "DRESDEFFXXX"

	fact iban(50080000, 5814706100L) should throw AccountValidationException

	fact iban(50080000, 6814706100L) should be "DE77500800006814706100"
	fact bic(50080000, 6814706100L) should be "DRESDEFFXXX"

	fact iban(50080000, 7814706100L) should be "DE32500800007814706100"
	fact bic(50080000, 7814706100L) should be "DRESDEFFXXX"

	fact iban(50080000, 8814706100L) should be "DE84500800008814706100"
	fact bic(50080000, 8814706100L) should be "DRESDEFFXXX"

	fact iban(50080000, 9814706100L) should be "DE39500800009814706100"
	fact bic(50080000, 9814706100L) should be "DRESDEFFXXX"

	fact iban(50080000, 23165400) should be "DE42500800000023165400"
	fact bic(50080000, 23165400) should be "DRESDEFFXXX"

	fact iban(50080000, 231654) should be "DE42500800000023165400"
	fact bic(50080000, 231654) should be "DRESDEFFXXX"

	fact iban(50080000, 4350300) should be "DE21500800000004350300"
	fact bic(50080000, 4350300) should be "DRESDEFFXXX"

	fact iban(50080000, 43503) should be "DE21500800000004350300"
	fact bic(50080000, 43503) should be "DRESDEFFXXX"

	fact iban(50080000, 526400) should throw AccountValidationException

	fact iban(50089400, 526400) should be "DE49500894000000526400"
	fact bic(50089400, 526400) should be "DRESDEFFI01"

	fact iban(10080000, 998761700) should be "DE73100800000998761700"
	fact bic(10080000, 998761700) should be "DRESDEFF100"

	fact iban(12080000, 998761700) should throw IbanCreationImpossibleException

	fact iban(26580070, 43434280) should throw AccountValidationException
	fact iban(26580070, 343428000) should throw AccountValidationException

	fact iban(26580070, 99021000) should be "DE10265800709902100000"
	fact bic(26580070, 99021000) should be "DRESDEFF265"

	//Institut 4
	fact iban(50540028, 4217386) should be "DE24505400280421738600"
	fact bic(50540028, 4217386) should be "COBADEFFXXX"

	fact iban(50540028, 4217387) should throw AccountValidationException

	fact iban(72040046, 111198800) should be "DE10720400460111198800"
	fact bic(72040046, 4217386) should be "COBADEFFXXX"

	fact iban(50540028, 420086100) should be "DE46505400280420086100"
	fact bic(50540028, 420086100) should be "COBADEFFXXX"

	fact iban(50540028, 421573704) should be "DE13505400280421573704"
	fact bic(50540028, 421573704) should be "COBADEFFXXX"

	fact iban(50540028, 421679200) should be "DE26505400280421679200"
	fact bic(50540028, 421679200) should be "COBADEFFXXX"

	fact iban(65440087, 130023500) should be "DE63654400870130023500"
	fact bic(65440087, 130023500) should be "COBADEFFXXX"

	fact iban(23040022, 104414) should be "DE29230400220010441400"
	fact bic(23040022, 104414) should be "COBADEFFXXX"

	fact iban(23040022, 104417) should throw AccountValidationException

	fact iban(12040000, 40050700) should be "DE27120400000040050700"
	fact bic(12040000, 40050700) should be "COBADEFFXXX"

	fact iban(23040022, 101337) should be "DE73230400220010133700"
	fact bic(23040022, 101337) should be "COBADEFFXXX"

	fact iban(23040022, 10503101) should be "DE77230400220010503101"
	fact bic(23040022, 10503101) should be "COBADEFFXXX"

	fact iban(12040000, 52065002) should be "DE12120400000052065002"
	fact bic(12040000, 52065002) should be "COBADEFFXXX"

	fact iban(50040000, 930125001) should be "DE97500400000930125001"
	fact bic(50040000, 930125001) should be "COBADEFFXXX"

	fact iban(70040041, 930125000) should be "DE89700400410930125000"
	fact bic(70040041, 930125000) should be "COBADEFFXXX"

	fact iban(50040000, 930125006) should be "DE59500400000930125006"
	fact bic(50040000, 930125006) should be "COBADEFFXXX"

	fact iban(50040033, 930125004) should throw IbanCreationImpossibleException

	fact iban(20041111, 130023500) should be "DE81200411110130023500"
	fact bic(20041111, 130023500) should be "COBADEFFXXX"

	fact iban(37080040, 111) should be "DE69370800400215022000"

	fact iban(50040000, 101010) should be "DE46500400000311011100"
	fact bic(50040000, 101010) should be "COBADEFFXXX"

	//from iban 31
	//1
	fact iban(545_200_71,6790149813L) should be "DE77545201946790149813"
	fact bic(545_200_71,6790149813L) should be "HYVEDEMM483"
	//2
	fact iban(54520071, 6791000000L) should throw AccountValidationException
	//3
	fact iban(10120760, 897) should throw IbanCreationImpossibleException
	
	//A
	fact iban(790_203_25,1210100047L) should be "DE70762200731210100047"
	fact bic(790_203_25,1210100047L) should be "HYVEDEMM419"
	//B
	fact iban(700_200_01,1210100047L) should be "DE70762200731210100047"
	fact bic(700_200_01,1210100047L) should be "HYVEDEMM419"
	//C
	fact iban(760_202_14,1210100047L) should be "DE70762200731210100047"
	fact bic(760_202_14,1210100047L) should be "HYVEDEMM419"
	//D
	fact iban(762_200_73,1210100047L) should be "DE70762200731210100047"
	fact bic(762_200_73,1210100047L) should be "HYVEDEMM419"
	//E ist absolut identisch zu A
	//F
	fact iban(630_204_50,44613352) should throw IbanCreationImpossibleException
	fact bic(630_204_50,44613352) should throw IbanCreationImpossibleException
	//G
	fact iban(660_201_50,1457032621) should be "DE92660202861457032621"
	fact bic(660_201_50,1457032621) should be "HYVEDEMM475"
	//H
	fact iban(790_203_25,1210100040) should throw AccountValidationException
	fact bic(790_203_25,1210100040) should throw AccountValidationException
	//from iban 32
	//J==D
	//K
	fact iban(660_202_86,1457032621) should be "DE92660202861457032621"
	fact bic(660_202_86,1457032621) should be "HYVEDEMM475"
	//L
	fact iban(762_200_73,3200000012L) should be "DE06710221823200000012"
	fact bic(762_200_73,3200000012L) should be "HYVEDEMM453"
	//M
	fact iban(100_208_90,5951950) should be "DE07100208900005951950"
	fact bic(100_208_90,5951950) should be "HYVEDEMM488"
	//N
	fact iban(100_208_90,897) should throw AccountValidationException
	fact bic(100_208_90,897) should throw AccountValidationException
	//O
	fact iban(850_200_86,847321750) should throw IbanCreationImpossibleException
	fact bic(850_200_86,847321750) should throw IbanCreationImpossibleException
	//from iban 33
	//P1
	fact iban(700_202_70,2950219435L) should be "DE76700202702950219435"
	fact bic(700_202_70,2950219435L) should be "HYVEDEMMXXX"
	//P2
	fact iban(700_202_70,1457032621) should be "DE92660202861457032621"
	fact bic(700_202_70,1457032621) should be "HYVEDEMM475"
	//P3
	fact iban(700_202_70,897) should be "DE55700202700000000897"
	fact bic(700_202_70,897) should be "HYVEDEMMXXX"
	//P4
	fact iban(700_202_70,847321750) should be "DE36700202700847321750"
	fact bic(700_202_70,847321750) should be "HYVEDEMMXXX"
	//P5 //BLZ im Test war gar nicht gültig, es gibt außer 700_202_70 keine mit Regel 33
	//Spendenkonten
	fact iban(700_202_70,22222) should be "DE11700202705803435253"
	fact bic(700_202_70,22222) should be "HYVEDEMMXXX"
	fact iban(700_202_70,1111111) should be "DE88700202700039908140"
	fact bic(700_202_70,1111111) should be "HYVEDEMMXXX"
	fact iban(700_202_70,94) should be "DE83700202700002711931"
	fact bic(700_202_70,94) should be "HYVEDEMMXXX"
	fact iban(700_202_70,7777777) should be "DE40700202705800522694"
	fact bic(700_202_70,7777777) should be "HYVEDEMMXXX"
	fact iban(700_202_70,55555) should be "DE61700202705801800000"
	fact bic(700_202_70,55555) should be "HYVEDEMMXXX"
	//from iban 34
	//Q1
	fact iban(600_202_90,1320815432) should be "DE69600202901320815432"
	fact bic(600_202_90,1320815432) should be "HYVEDEMM473"
	//Q2
	fact iban(600_202_90,1457032621) should be "DE92660202861457032621"
	fact bic(600_202_90,1457032621) should be "HYVEDEMM475"
	//Q3
	fact iban(600_202_90,5951950) should be "DE67600202900005951950"
	fact bic(600_202_90,5951950) should be "HYVEDEMM473"
	//Q4
	fact iban(600_202_90,847321750) should throw IbanCreationImpossibleException
	fact bic(600_202_90,847321750) should throw IbanCreationImpossibleException
	//Spendenkonten
	fact iban(600_202_90,500500500) should be "DE82600202904340111112"
	fact bic(600_202_90,500500500) should be "HYVEDEMM473"
	fact iban(600_202_90,502) should be "DE28600202904340118001"
	fact bic(600_202_90,502) should be "HYVEDEMM473"
	//from iban 35
	//R1
	fact iban(790_200_76,1050958422) should be "DE42790200761050958422"
	fact bic(790_200_76,1050958422) should be "HYVEDEMM455"
	//R2
	fact iban(790_200_76,1320815432) should be "DE69600202901320815432"
	fact bic(790_200_76,1320815432) should be "HYVEDEMM473"
	//R3
	fact iban(790_200_76,5951950) should be "DE56790200760005951950"
	fact bic(790_200_76,5951950) should be "HYVEDEMM455"
	//R4
	fact iban(790_200_76,847321750) should throw IbanCreationImpossibleException
	fact bic(790_200_76,847321750) should throw IbanCreationImpossibleException
	//Spendenkonten
	fact iban(790_200_76,9696) should be "DE29790200761490196966"
	fact bic(790_200_76,9696) should be "HYVEDEMM455"



	def iban(int blz, long account){
		subject.getIban(new LegacyAccount(blz, account)).doubleCheck.iban
	}
	def bic(int blz, long account){
		subject.getIban(new LegacyAccount(blz, account)).bic
	}
}
