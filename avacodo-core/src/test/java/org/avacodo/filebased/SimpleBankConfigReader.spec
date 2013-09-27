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
package org.avacodo.filebased

import java.nio.charset.Charset
import org.avacodo.model.UnknownBankCodeException

describe SimpleBankConfigReader {

	static SimpleBankConfigReader subject=new SimpleBankConfigReader(
		typeof(SimpleBankConfigReader).classLoader.getResource("BLZ2_20130603.txt"), Charset::forName("ISO-8859-1")
	)

	facts "10120760"{
		val it =subject.getBankConfig(10120760)
		accountCheckMethod should be "99"
		ibanRule should be 31
		ibanRuleVersion should be 0
		deletionAnnounced should be true
		succeedingBlz should be 10020890
		bic should be "HYVEDEM1079"
	}

	//deleted blz
	fact subject.getBankConfig(21064045) should throw UnknownBankCodeException

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