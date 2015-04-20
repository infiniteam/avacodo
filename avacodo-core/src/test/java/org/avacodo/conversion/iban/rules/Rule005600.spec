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

import org.avacodo.conversion.iban.IbanCreationImpossibleException
import org.avacodo.model.LegacyAccount

import static extension org.avacodo.conversion.iban.rules.CheckMethodConfig.*
import static extension org.avacodo.conversion.iban.rules.DoubleChecker.*

describe Rule005600{
	
	before{subject=new Rule005600}

	//valid checksum but not a 10 digit account number
	fact iban(10010111, 929070100,"13") should throw IbanCreationImpossibleException
	fact iban(86010111, 4200,"13") should throw IbanCreationImpossibleException
	fact iban(16010111, 52065002,"13") should throw IbanCreationImpossibleException
	
	fact iban(20010111, 9929070144L,"13") should be "DE83200101119929070144"
	
	fact iban(21010111, 36,"13") should be "DE29380101111010240003"
	fact iban(22010111, 50,"13") should be "DE55480101111328506100"
	fact iban(23010111, 99,"13") should be "DE26430101111826063000"
	fact iban(25010111, 110,"13") should be "DE52250101111015597802"
	fact iban(25410111, 240,"13") should be "DE13380101111010240000"
	fact iban(25910111, 333,"13") should be "DE15380101111011296100"
	fact iban(26010111, 555,"13") should be "DE54100101111600220800"
	fact iban(26510111, 556,"13") should be "DE42390101111000556100"
	fact iban(27010111, 606,"13") should be "DE70250101111967153801"
	fact iban(28010111, 700,"13") should be "DE92265101111070088000"
	fact iban(29010111, 777,"13") should be "DE72250101111006015200"
	fact iban(29210111, 999,"13") should be "DE83380101111010240001"
	fact iban(30010111, 1234,"13") should be "DE91250101111369152400"
	fact iban(31010111, 1313,"13") should be "DE48570101111017500000"
	fact iban(33010111, 1888,"13") should be "DE81370101111241113000"	
	fact iban(35010111, 1953,"13") should be "DE30250101111026500901"
	fact iban(35211012, 1998,"13") should be "DE47670101111547620500"
	fact iban(36010111, 2007,"13") should be "DE62250101111026500907"
	fact iban(36010111, 4004,"13") should be "DE45370101111635100100"
	fact iban(36210111, 4444,"13") should be "DE88670101111304610900"
	fact iban(37010111, 5000,"13") should be "DE20250101111395676000"
	fact iban(38010111, 5510,"13") should be "DE96290101111611754300"
	fact iban(39010111, 6060,"13") should be "DE43500101111000400200"
	fact iban(40010111, 6800,"13") should be "DE02670101111296401301"
	fact iban(41010111, 55555,"13") should be "DE13380101111027758200"
	fact iban(42010111, 60000,"13") should be "DE98500101111005007001"
	fact iban(42610112, 66666,"13") should be "DE10200101111299807801"
	fact iban(43010111, 102030,"13") should be "DE59370101111837501600"
	fact iban(44010111, 121212,"13") should be "DE48700101111249461502"
	fact iban(46010111, 130500,"13") should be "DE78300101111413482100"
	fact iban(48010111, 202020,"13") should be "DE24370101111213431002"
	fact iban(50010111, 414141,"13") should be "DE59380101111010555101"
	fact iban(50510111, 666666,"13") should be "DE49200101111798758900"
	fact iban(51010111, 5000000,"13") should be "DE62370101111403124100"
	fact iban(51310111, 500500500,"13") should be "DE17600101111045720000"
	
	def iban(int blz,long konto, String checkMethod){
		val config=checkMethod.configMarkedDeleted
		subject.applyTo(new LegacyAccount(blz, konto),config).doubleCheck.iban
	}

}
