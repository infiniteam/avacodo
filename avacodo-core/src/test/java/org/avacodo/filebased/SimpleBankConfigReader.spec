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
package org.avacodo.filebased

import java.nio.charset.Charset
import org.avacodo.model.UnknownBankCodeException

describe SimpleBankConfigReader {

	static SimpleBankConfigReader subject=new SimpleBankConfigReader(
		typeof(SimpleBankConfigReader).classLoader.getResource("test.txt"), Charset::forName("ISO-8859-1")
	)

	facts "99999999"{
		val it =subject.getBankConfig(99999999)
		accountCheckMethod should be "02"
		ibanRule should be 99
		ibanRuleVersion should be 32
		deletionAnnounced should be true
		succeedingBlz should be 99999998
		bic should be "ABCDEFGHIJK"
	}

	facts "99999998"{
		val it =subject.getBankConfig(99999998)
		accountCheckMethod should be "03"
		ibanRule should be 99
		ibanRuleVersion should be 32
		deletionAnnounced should be false
		succeedingBlz should be null
		bic should be "ABCDEFGHIJK"
	}

	//deleted blz
	fact subject.getBankConfig(99999997) should throw UnknownBankCodeException

//	fact "ignore"{
//		subject.knownBankCodes.forEach[
//			val config=subject.getBankConfig(it)
//			if(config.accountCheckMethod=="D2"){
//				println(it+" "+config.ibanRule)
//			}
//			if(config.ibanRule==31){
//				println(it + " " + config.accountCheckMethod + " "+config.bic +" "+config.succeedingBlz)
//			}
//		]
//		1 should be 1
//	}
}
