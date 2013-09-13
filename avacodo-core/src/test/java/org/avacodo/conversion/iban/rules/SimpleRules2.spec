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
package org.avacodo.conversion.iban.rules

import org.avacodo.conversion.iban.IbanCreationImpossibleException
import org.avacodo.model.DeIban
import org.avacodo.model.LegacyAccount
import org.avacodo.validation.account.AccountValidationException

import static extension org.avacodo.conversion.iban.rules.DoubleChecker.*
import static extension org.avacodo.conversion.iban.rules.SimpleRulesHelper.*

describe RuleBasedIbanCalculator "SimpleRules2"{

	def{
		|    rule|check|     blz|      konto|                         Iban|          Bic|
		//National Bank 35020030, 36220030, 36520030, check method 10
		|"002100"| "10"|35020030|     305200|"DE81 3602 0030 0000 3052 00"|"NBAGDE3EXXX"|
		|"002100"| "10"|36220030|     900826|"DE03 3602 0030 0000 9008 26"|"NBAGDE3EXXX"|
		|"002100"| "10"|36520030|     705020|"DE71 3602 0030 0000 7050 20"|"NBAGDE3EXXX"|
		|"002100"| "10"|36020030|    9197354|"DE18 3602 0030 0009 1973 54"|"NBAGDE3EXXX"|
		|"002100"| "10"|36020030|          1|                         null|         null|//errata
		|"002100"| "10"|36020030|         12|                         null|         null|//errata
		|"002100"| "10"|36020030|        123|                         null|         null|//errata
		|"002100"| "10"|36020030|       1234|                         null|         null|//errata
		|"002100"| "10"|36020030|      12345|                         null|         null|//errata
		|"002100"| "10"|36020030|   12345678|                         null|         null|//errata
		|"002101"| "10"|35020030|     305200|"DE81 3602 0030 0000 3052 00"|"NBAGDE3EXXX"|
		|"002101"| "10"|36220030|     900826|"DE03 3602 0030 0000 9008 26"|"NBAGDE3EXXX"|
		|"002101"| "10"|36520030|     705020|"DE71 3602 0030 0000 7050 20"|"NBAGDE3EXXX"|
		|"002101"| "10"|36020030|    9197354|"DE18 3602 0030 0009 1973 54"|"NBAGDE3EXXX"|
		|"002101"| "10"|36020030|          1|                         null|         null|
		|"002101"| "10"|36020030|         12|                         null|         null|
		|"002101"| "10"|36020030|        123|                         null|         null|
		|"002101"| "10"|36020030|       1234|                         null|         null|
		|"002101"| "10"|36020030|      12345|                         null|         null|
		|"002101"| "10"|36020030|   12345678|                         null|         null|
		//Gls Gemeinschaftsbank, , check method 34 und 88
		|"002200"| "34"|43060967|    1111111|"DE22 4306 0967 2222 2000 00"|         null|
		//Volksbank Osnabrück 26590025, check method 28
		|"002300"| "28"|26590025|        700|"DE76 2659 0025 1000 7008 00"|         null|
		//Bank im Bistum Essen 36060295, check method 06
		|"002400"| "06"|36060295|         94|"DE48 3606 0295 0000 0016 94"|         null|
		|"002400"| "06"|36060295|        248|"DE03 3606 0295 0000 0172 48"|         null|
		|"002400"| "06"|36060295|        345|"DE03 3606 0295 0000 0173 45"|         null|
		|"002400"| "06"|36060295|        400|"DE75 3606 0295 0000 0144 00"|         null|
		//Baden Würtemberg Bank many bank codes with divers check methods
		|"002500"| "01"|64150182|    2777939|"DE81 6005 0101 0002 7779 39"|         null|
		|"002500"| "01"|64450288|7893500686L|"DE80 6005 0101 7893 5006 86"|         null|
		//KD Bank 35060190, check method 06 (special accounts 09)
		|"002600"| "06"|35060190|      55111|"DE21 3506 0190 0000 0551 11"|         null|
		|"002600"| "06"|35060190|    8090100|"DE86 3506 0190 0008 0901 00"|         null|
		//Volksbank Krefeld 32060362, check method 06
		|"002700"| "06"|32060362|       3333|"DE47 3206 0362 0000 0033 33"|         null|
		|"002700"| "06"|32060362|       4444|"DE23 3206 0362 0000 0044 44"|         null|
		//Sparkasse Hannover 250500180, 25050299, check method A3
		|"002800"| "A3"|25050299|      17095|"DE69 2505 0180 0000 0170 95"|"SPKHDE2HXXX"|
		//Societe Generale 51210800, check method 09
		|"002900"| "09"|51210800|2600123456L|"DE02 5121 0800 0260 1234 56"|         null|
		|"002900"| "09"|51210800|1410123456L|"DE35 5121 0800 0141 1234 56"|         null|
		//HSH Nordbank 20050000, 21050000, 23050000, check method C5
		|"003600"| "C5"|20050000|     101105|"DE32 2105 0000 0101 1050 00"|         null|
		|"003600"| "C5"|21050000|     840132|"DE91 2105 0000 0840 1320 00"|         null|
		|"003600"| "C5"|23050000|     631879|"DE81 2105 0000 0631 8790 00"|         null|
		|"003600"| "C5"|20050000|   30000025|"DE75 2105 0000 0030 0000 25"|         null|
		|"003600"| "C5"|23050000|   51300528|"DE76 2105 0000 0051 3005 28"|         null|

		|"003600"| "C5"|20050000|  100271010|"DE85 2105 0000 0100 2710 10"|         null|
		|"003600"| "C5"|23050000|  319574000|"DE55 2105 0000 0319 5740 00"|         null|

		|"003600"| "C5"|20050000|3060000035L|"DE13 2105 0000 3060 0000 35"|         null|
		|"003600"| "C5"|23050000|3070010313L|"DE09 2105 0000 3070 0103 13"|         null|
		|"003600"| "C5"|23050000|1100001928L|"DE51 2105 0000 1100 0019 28"|         null|
		|"003600"| "C5"|20050000|7052000037L|"DE82 2105 0000 7052 0000 37"|         null|
		|"003600"| "C5"|23050000|7053001166L|"DE07 2105 0000 7053 0011 66"|         null|
		|"003600"| "C5"|20050000|8553002045L|"DE20 2105 0000 8553 0020 45"|         null|
		|"003600"| "C5"|23050000|8553009087L|"DE06 2105 0000 8553 0090 87"|         null|
		|"003600"| "C5"|20050000|9000010040L|"DE37 2105 0000 9000 0100 40"|         null|
		|"003600"| "C5"|23050000|9000906093L|"DE55 2105 0000 9000 9060 93"|         null|
		//Bank of Tokyo 20110700, 30010700, check method 09; (30010700 00)
		|"003700"| "09"|20110700|     123456|"DE41 3001 0700 0000 1234 56"|"BOTKDEDXXXX"|
		|"003700"| "09"|30010700|     654321|"DE85 3001 0700 0000 6543 21"|         null|//bic override necessary if bank code is overridden
//		//Ostfriesische Volksbank div, check method 28?
		|"003800"| "28"|26691213|          1|"DE52 2859 0075 0000 0000 01"|"GENODEF1LER"|
		|"003800"| "28"|28591579|          1|"DE52 2859 0075 0000 0000 01"|"GENODEF1LER"|
		|"003800"| "28"|25090300|          1|"DE52 2859 0075 0000 0000 01"|"GENODEF1LER"|
		//Sparkasse Staufen Breisach 68052328,  check method 00
		|"004000"| "00"|68051310|    6015002|"DE17 6805 2328 0006 0150 02"|"SOLADES1STF"| //bic from errata
		|"004001"| "00"|68051310|    6015002|"DE17 6805 2328 0006 0150 02"|"SOLADES1STF"|
		//Bausparkasse Schw. Hall 62220000,  check method 09
		|"004100"| "09"|62220000|   62220000|"DE96 5006 0400 0000 0114 04"|"GENODEFFXXX"|
		|"004100"| "09"|62220000| 1234567890|"DE96 5006 0400 0000 0114 04"|"GENODEFFXXX"|
		//Sparkasse Freiburg 68050101,  check method 01
		|"004400"| "01"|68050101|        202|"DE51 6805 0101 0002 2820 22"|         null|
		// VON ESSEN 10120800, 27010200, 36010200, 60020300,  check method 09
		|"004800"| "09"|10120800| 1231234567|"DE12 3601 0200 1231 2345 67"|"VONEDE33XXX"|
		|"004800"| "09"|27010200| 1234567890|"DE92 3601 0200 1234 5678 90"|"VONEDE33XXX"|
		|"004800"| "09"|36010200|         49|"DE84 3601 0200 0000 0000 49"|"VONEDE33XXX"|

		}
facts{
		examples.forEach[
			try{
				val trimmed=iban.trimmed
				val conf=config(check,rule)
				subject=conf.calculator
				var result=subject.getIban(new LegacyAccount(blz,konto)).doubleCheck

				result.iban should be trimmed
				if(bic===null){
					result.bicOverriddenByIbanRule should be false
					result.bic should be conf.bic
				} else{
					result.bicOverriddenByIbanRule should be true
					result.bic should be bic
				}
				DeIban::validate(trimmed) should be true
			}catch(AccountValidationException e){
//				e.printStackTrace
				iban should contain "checksum"
			}catch(IbanCreationImpossibleException e){
//				e.printStackTrace
				iban should be null as String
			}
		]
	}
}