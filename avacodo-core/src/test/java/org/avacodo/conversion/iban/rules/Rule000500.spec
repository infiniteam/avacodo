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

import static extension org.avacodo.conversion.iban.rules.CheckMethodConfig.*
import static extension org.avacodo.conversion.iban.rules.DoubleChecker.*

describe Rule000500 {

	before{subject=new Rule000500}

	//main account too short
	fact iban(50080000, 23L) should throw AccountValidationException
	//00 must be added
	fact ibanDoubleCheck(50080000, 65432) should endWith "6543200"
	//ambiguous another 00 could be added, TODO subject to discussion
	//we decided that in this case we do not care about ambiguity
	fact ibanDoubleCheck(50080000, 6543200) should endWith "6543200"
	//valid without ambiguity
	fact ibanDoubleCheck(50080000, 654320000) should endWith "654320000"

	fact subject.check76("0123456") should be true
	fact subject.check76("0065432") should be true
	fact subject.check76("6543200") should be true

	def boolean should_move(Rule000500 sub, String account){
		sub.canBeMoved76(new RichIbanResult(new LegacyAccount("12345678",account),"09".config))
	}
	fact subject should move "1"
	fact subject should move "10"
	fact subject should move "100"
	fact subject should move "1000"
	fact subject should move "10000"
	fact subject should move "100000"
	fact subject should move "1000000"
	fact subject should move "10000000"

	//9 digit
	fact subject should not move "100000000"

	//10 digit
	fact subject should not move "1000000000"
	fact subject should not move "1000000001"
	fact subject should not move "1000000010"
	fact subject should not move "1000000100"
	fact subject should not move "1000001000"
	fact subject should not move "1000010000"
	fact subject should not move "1000100000"
	fact subject should not move "1001000000"
	fact subject should not move "1010000000"
	fact subject should not move "1100000000"

	fact ibanDoubleCheck(26580070, 732502200) should be "DE32265800700732502200"
	fact ibanDoubleCheck(26580070, 7325022) should be "DE32265800700732502200"
	fact ibanDoubleCheck(26580070, 8732502200L) should be "DE60265800708732502200"
	fact ibanDoubleCheck(26580070, 4820379900L) should be "DE37265800704820379900"

	fact iban(50080000, 1814706100L) should throw AccountValidationException
	fact iban(50080000, 2814706100L) should throw AccountValidationException
	fact iban(50080000, 3814706100L) should throw AccountValidationException
	fact ibanDoubleCheck(50080000, 4814706100L) should be "DE70500800004814706100"
	fact iban(50080000, 5814706100L) should throw AccountValidationException
	fact ibanDoubleCheck(50080000, 6814706100L) should be "DE77500800006814706100"
	fact ibanDoubleCheck(50080000, 7814706100L) should be "DE32500800007814706100"
	fact ibanDoubleCheck(50080000, 8814706100L) should be "DE84500800008814706100"
	fact ibanDoubleCheck(50080000, 9814706100L) should be "DE39500800009814706100"

	fact ibanDoubleCheck(50080000, 23165400) should be "DE42500800000023165400"
	fact ibanDoubleCheck(50080000, 231654) should be "DE42500800000023165400"
	fact ibanDoubleCheck(50080000, 4350300) should be "DE21500800000004350300"
	fact ibanDoubleCheck(50080000, 43503) should be "DE21500800000004350300"

	fact iban(50080000, 526400) should throw AccountValidationException
	fact ibanDoubleCheck(50089400, 526400) should be "DE49500894000000526400"

	fact ibanDoubleCheck(10080000, 998761700) should be "DE73100800000998761700"
	fact iban(12080000, 998761700) should throw IbanCreationImpossibleException

	fact iban(26580070, 43434280) should throw AccountValidationException
	fact iban(26580070, 4343428000L) should throw AccountValidationException // test data added in version 0005 01
	fact iban(26580070, 343428000) should throw AccountValidationException

	fact ibanDoubleCheck(26580070, 99021000) should be "DE10265800709902100000"

	//Institut 4
	fact ibanDoubleCheck(50540028, 4217386) should be "DE24505400280421738600"
	fact bic(50540028, 4217386) should be "COBADEFFXXX"

	fact iban(50540028, 4217387) should throw AccountValidationException

	fact ibanDoubleCheck(72040046, 111198800) should be "DE10720400460111198800"
	fact bic(72040046, 4217386) should be "COBADEFFXXX"

	fact ibanDoubleCheck(50540028, 420086100) should be "DE46505400280420086100"
	fact bic(50540028, 420086100) should be "COBADEFFXXX"

	fact ibanDoubleCheck(50540028, 421573704) should be "DE13505400280421573704"
	fact bic(50540028, 421573704) should be "COBADEFFXXX"

	fact ibanDoubleCheck(50540028, 421679200) should be "DE26505400280421679200"
	fact bic(50540028, 421679200) should be "COBADEFFXXX"

	fact ibanDoubleCheck(65440087, 130023500) should be "DE63654400870130023500"
	fact bic(65440087, 130023500) should be "COBADEFFXXX"

	fact ibanDoubleCheck(23040022, 104414) should be "DE29230400220010441400"
	fact bic(23040022, 104414) should be "COBADEFFXXX"

	fact iban(23040022, 104417) should throw AccountValidationException

	fact ibanDoubleCheck(12040000, 40050700) should be "DE27120400000040050700"
	fact bic(12040000, 40050700) should be "COBADEFFXXX"

	fact ibanDoubleCheck(23040022, 101337) should be "DE73230400220010133700"
	fact bic(23040022, 101337) should be "COBADEFFXXX"

	fact ibanDoubleCheck(23040022, 10503101) should be "DE77230400220010503101"
	fact bic(23040022, 10503101) should be "COBADEFFXXX"

	fact ibanDoubleCheck(12040000, 52065002) should be "DE12120400000052065002"
	fact bic(12040000, 52065002) should be "COBADEFFXXX"

	fact ibanDoubleCheck(50040000, 930125001) should be "DE97500400000930125001"
	fact bic(50040000, 930125001) should be "COBADEFFXXX"

	fact ibanDoubleCheck(70040041, 930125000) should be "DE89700400410930125000"
	fact bic(70040041, 930125000) should be "COBADEFFXXX"

	fact ibanDoubleCheck(50040000, 930125006) should be "DE59500400000930125006"
	fact bic(50040000, 930125006) should be "COBADEFFXXX"

	fact iban(10045050, 930125001) should throw IbanCreationImpossibleException
	fact iban(50040033, 930125004) should throw IbanCreationImpossibleException
	fact iban(70045050, 930125007) should throw IbanCreationImpossibleException

	fact ibanDoubleCheck(20041111, 130023500) should be "DE81200411110130023500"
	fact bic(20041111, 130023500) should be null

	fact ibanDoubleCheck(37080040, 111) should be "DE69370800400215022000"

	fact ibanDoubleCheck(50040000, 101010) should be "DE46500400000311011100"
	fact bic(50040000, 101010) should be "COBADEFFXXX"

	// donation account numbers; 7 digit account number in donation map is converted to 9 digit
	// account number during IBAN calculation
	fact ibanDoubleCheck(55040022, 555) should be "DE81550400220211050000"
	fact ibanDoubleCheck(55040022, 343434) should be "DE78550400220217900000"
	fact ibanDoubleCheck(38040007, 909090) should be "DE65380400070119160000"

	def iban(int blz,long konto){
		val checkMethod=blz.checkMethod
		subject.applyTo(new LegacyAccount(blz, konto),checkMethod.config).iban
	}
	def ibanDoubleCheck(int blz,long konto){
		val checkMethod=blz.checkMethod
		subject.applyTo(new LegacyAccount(blz, konto),checkMethod.config).doubleCheck.iban
	}
	def bic(int blz,long konto){
		val checkMethod=blz.checkMethod
		subject.applyTo(new LegacyAccount(blz, konto),checkMethod.config).bic
	}

	def getCheckMethod(int blz){
		switch blz{
			case 26580070:"76"
			case 50080000:"76"
			case 50540020:"13"
			case 50089400:"09"
			case 10080000:"76"
			case 12080000:"76"
			case 50540028:"13"
			case 72040046:"13"
			case 65440087:"13"
			case 23040022:"13"
			case 12040000:"13"
			case 50040000:"13"
			case 70040041:"13"
			case 10045050:"13"
			case 20041111:"13"
			case 37080040:"76"
			case 70045050:"13"
			case 50040033:"09"
			case 55040022:"13"
			case 38040007:"13"
			default: throw new IllegalArgumentException("unknown blz")
		}
	}
}
