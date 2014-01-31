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

import org.avacodo.conversion.iban.IbanCreationImpossibleException
import org.avacodo.model.DeIban
import org.avacodo.model.LegacyAccount
import org.avacodo.validation.account.AccountValidationException

import static extension org.avacodo.conversion.iban.rules.DoubleChecker.*
import static extension org.avacodo.conversion.iban.rules.SimpleRulesHelper.*

describe RuleBasedIbanCalculator "SimpleRules3"{

	def{
		|    rule|check|     blz|      konto|                         Iban|          Bic|
		// Sparkasse LeerWittmund 28252760,  check method 00
		|"005000"| "00"|28252760|  130084981|"DE24 2855 0000 0130 0849 81"|"BRLADE21LER"|
		// FIXME bank code ? Landesbank BW / BW Bank, check method 01 
		|"005100"| "01"|60050101|        333|"DE96 6005 0101 7832 5008 81"|         null|
		|"005100"| "01"|60050101|        502|"DE15 6005 0101 0001 1088 84"|         null|
		|"005100"| "01"|60050101|  500500500|"DE25 6005 0101 0005 0050 00"|         null|
		|"005100"| "01"|60050101|  502502502|"DE15 6005 0101 0001 1088 84"|         null|
		// Landesbank BW / BW Bank, check method 65
		|"005200"| "65"|67220020|5308810004L|"DE38 6005 0101 0002 6626 04"|         null|
		|"005200"| "65"|67220020|5308810000L|"DE54 6005 0101 0002 6596 00"|         null|
		|"005200"| "65"|67020020|5203145700L|"DE22 6005 0101 7496 5109 94"|         null|
		|"005200"| "65"|69421020|6208908100L|"DE85 6005 0101 7481 5013 41"|         null|
		|"005200"| "65"|66620020|4840404000L|"DE13 6005 0101 7498 5026 63"|         null|
		|"005200"| "65"|64120030| 1201200100|"DE28 6005 0101 7477 5012 14"|         null|
		|"005200"| "65"|64020030| 1408050100|"DE82 6005 0101 7469 5345 05"|         null|
		|"005200"| "65"|63020130| 1112156300|"DE69 6005 0101 0004 4756 55"|         null|
		|"005200"| "65"|62030050|7002703200L|"DE72 6005 0101 7406 5011 75"|         null|	
		|"005200"| "65"|69220020|6402145400L|"DE91 6005 0101 7485 5002 52"|         null|
			
		|"005200"| "65"|69220020|5308810004L|                         null|         null|
		|"005200"| "65"|69421020|5308810000L|                         null|         null|
		|"005200"| "65"|62030050|5203145700L|                         null|         null|
		|"005200"| "65"|69220020|6208908100L|                         null|         null|
		|"005200"| "65"|62030050|4840404000L|                         null|         null|
		|"005200"| "65"|67220020| 1201200100|                         null|         null|
		|"005200"| "65"|62030050| 1408050100|                         null|         null|
		|"005200"| "65"|62030050| 1112156300|                         null|         null|
		|"005200"| "65"|64120030|7002703200L|                         null|         null|
		|"005200"| "65"|67220020|6402145400L|                         null|         null|
		|"005200"| "65"|67220020| 1234567400|                         null|         null|
		// Landesbank BW / BW Bank 55050000, check method 59
		|"005300"| "59"|55050000|      35000|"DE94 6005 0101 7401 5559 13"|         null|
		|"005300"| "59"|55050000|  119345106|"DE89 6005 0101 7401 5559 06"|         null|
		|"005300"| "59"|55050000|        908|"DE31 6005 0101 7401 5074 80"|         null|
		|"005300"| "59"|55050000|        901|"DE57 6005 0101 7401 5074 97"|         null|
		|"005300"| "59"|55050000|        910|"DE21 6005 0101 7401 5074 66"|         null|
		|"005300"| "59"|55050000|      35100|"DE94 6005 0101 7401 5559 13"|         null|
		|"005300"| "59"|55050000|        902|"DE26 6005 0101 7401 5074 73"|         null|
		|"005300"| "59"|55050000|      44000|"DE37 6005 0101 7401 5558 72"|         null|
		|"005300"| "59"|55050000|  110132511|"DE32 6005 0101 7401 5505 30"|         null|
		|"005300"| "59"|55050000|  110024270|"DE96 6005 0101 7401 5012 66"|         null|
		|"005300"| "59"|55050000|       3500|"DE94 6005 0101 7401 5559 13"|         null|
		|"005300"| "59"|55050000|  110050002|"DE53 6005 0101 7401 5022 34"|         null|
		|"005300"| "59"|55050000|   55020100|"DE37 6005 0101 7401 5558 72"|         null|
		|"005300"| "59"|55050000|  110149226|"DE14 6005 0101 7401 5122 48"|         null|
		// Landesbank BW / BW Bank 60020030, check method 65
		|"005300"| "65"|60020030| 1047444300|"DE82 6005 0101 7871 5383 95"|         null|
		|"005300"| "65"|60020030| 1040748400|"DE53 6005 0101 0001 3667 05"|         null|
		|"005300"| "65"|60020030| 1000617900|"DE21 6005 0101 0002 0099 06"|         null|
		|"005300"| "65"|60020030| 1003340500|"DE06 6005 0101 0002 0011 55"|         null|
		|"005300"| "65"|60020030| 1002999900|"DE59 6005 0101 0002 5889 91"|         null|
		|"005300"| "65"|60020030| 1004184600|"DE85 6005 0101 7871 5135 09"|         null|
		|"005300"| "65"|60020030| 1000919900|"DE66 6005 0101 7871 5315 05"|         null|
		|"005300"| "65"|60020030| 1054290000|"DE61 6005 0101 7871 5212 16"|         null|
		// Landesbank BW / BW Bank 60050000, check method 09
		|"005300"| "09"|60050000|       1523|"DE49 6005 0101 0001 3649 34"|         null|
		|"005300"| "09"|60050000|       2811|"DE17 6005 0101 0001 3674 50"|         null|
		|"005300"| "09"|60050000|       2502|"DE53 6005 0101 0001 3667 05"|         null|
		|"005300"| "09"|60050000|     250412|"DE56 6005 0101 7402 0515 88"|         null|
		|"005300"| "09"|60050000|       3009|"DE23 6005 0101 0001 3679 24"|         null|
		|"005300"| "09"|60050000|       4596|"DE48 6005 0101 0001 3728 09"|         null|
		|"005300"| "09"|60050000|       3080|"DE21 6005 0101 0002 0099 06"|         null|
		|"005300"| "09"|60050000|    1029204|"DE73 6005 0101 0002 7822 54"|         null|
		|"005300"| "09"|60050000|       3002|"DE23 6005 0101 0001 3679 24"|         null|
		|"005300"| "09"|60050000|     123456|"DE26 6005 0101 0001 3628 26"|         null|
		|"005300"| "09"|60050000|       2535|"DE66 6005 0101 0001 1198 97"|         null|
		|"005300"| "09"|60050000|       5500|"DE92 6005 0101 0001 3757 03"|         null|
		// Landesbank BW / BW Bank 66020020, check method 65
		|"005300"| "65"|66020020|4002401000L|"DE74 6005 0101 7495 5009 67"|         null|
		|"005300"| "65"|66020020|4000604100L|"DE28 6005 0101 0002 8100 30"|         null| 
		|"005300"| "65"|66020020|4002015800L|"DE02 6005 0101 7495 5301 02"|         null|
		|"005300"| "65"|66020020|4003746700L|"DE56 6005 0101 7495 5014 85"|         null|
		// Landesbank BW / BW Bank 66050000, check method 09
		|"005300"| "09"|66050000|      86567|"DE49 6005 0101 0001 3649 34"|         null|
		|"005300"| "09"|66050000|      86345|"DE56 6005 0101 7402 0466 41"|         null|
		|"005300"| "09"|66050000|      85304|"DE15 6005 0101 7402 0454 39"|         null|
		|"005300"| "09"|66050000|      85990|"DE56 6005 0101 7402 0515 88"|         null|
		// Landesbank BW / BW Bank 86050000, check method 09
		|"005300"| "09"|86050000|       1016|"DE80 6005 0101 7461 5001 28"|         null|
		|"005300"| "09"|86050000|       3535|"DE61 6005 0101 7461 5056 11"|         null|
		|"005300"| "09"|86050000|       2020|"DE43 6005 0101 7461 5000 18"|         null|
		|"005300"| "09"|86050000|       4394|"DE93 6005 0101 7461 5057 14"|         null|
		// Ev. Darlehnsgenossenschaft 10060237, 21060237, check method 33, see also Rule005401.spec
		|"005400"| "33"|10060237|        500|"DE35 1006 0237 0000 5005 00"|         null|
		|"005400"| "33"|21060237|        502|"DE26 2106 0237 0000 5025 02"|         null|
		|"005400"| "33"|10060237|      18067|"DE20 1006 0237 0000 1806 70"|         null|
		|"005400"| "33"|10060237|     484848|"DE80 1006 0237 0000 4848 49"|         null|
		|"005400"| "33"|10060237|     760440|"DE23 1006 0237 0000 1604 40"|         null|
		|"005400"| "33"|10060237|    1018413|"DE80 1006 0237 0010 1084 13"|         null|
		|"005400"| "33"|21060237|    2601577|"DE29 2106 0237 0026 0157 76"|         null|
		|"005400"| "33"|21060237|    5005000|"DE51 2106 0237 0000 5005 00"|         null|
		|"005400"| "33"|21060237|   10796740|"DE95 2106 0237 0010 7967 43"|         null|
		|"005400"| "33"|21060237|   11796740|"DE45 2106 0237 0011 7967 43"|         null|
		|"005400"| "33"|21060237|   12796740|"DE92 2106 0237 0012 7967 43"|         null|
		|"005400"| "33"|21060237|   13796740|"DE42 2106 0237 0013 7967 43"|         null|
		|"005400"| "33"|21060237|   14796740|"DE89 2106 0237 0014 7967 43"|         null|
		|"005400"| "33"|21060237|   15796740|"DE39 2106 0237 0015 7967 43"|         null|
		|"005400"| "33"|21060237|   16307000|"DE42 2106 0237 0163 1070 00"|         null|
		|"005400"| "33"|21060237|   16610700|"DE86 2106 0237 0166 1070 00"|         null|
		|"005400"| "33"|10060237|   16796740|"DE70 1006 0237 0016 7967 43"|         null|
		|"005400"| "33"|10060237|   17796740|"DE20 1006 0237 0017 7967 43"|         null|
		|"005400"| "33"|10060237|   18796740|"DE67 1006 0237 0018 7967 43"|         null|
		|"005400"| "33"|10060237|   19796740|"DE17 1006 0237 0019 7967 43"|         null|
		|"005400"| "33"|10060237|   20796740|"DE64 1006 0237 0020 7967 43"|         null|
		|"005400"| "33"|10060237|   21796740|"DE14 1006 0237 0021 7967 43"|         null|
		|"005400"| "33"|10060237|   22796740|"DE61 1006 0237 0022 7967 43"|         null|
		|"005400"| "33"|10060237|   23796740|"DE11 1006 0237 0023 7967 43"|         null|
		|"005400"| "33"|21060237|   24796740|"DE74 2106 0237 0024 7967 43"|         null|
		|"005400"| "33"|21060237|   25796740|"DE24 2106 0237 0025 7967 43"|         null|
		|"005400"| "33"|21060237|   26610700|"DE33 2106 0237 0266 1070 00"|         null|
		|"005400"| "33"|21060237|   26796740|"DE71 2106 0237 0026 7967 43"|         null|
		|"005400"| "33"|21060237|   27796740|"DE21 2106 0237 0027 7967 43"|         null|
		|"005400"| "33"|21060237|   28796740|"DE68 2106 0237 0028 7967 43"|         null|
		|"005400"| "33"|21060237|   29796740|"DE18 2106 0237 0029 7967 43"|         null|
		|"005400"| "33"|21060237|   45796740|"DE91 2106 0237 0045 7967 43"|         null|
		|"005400"| "33"|21060237|   50796740|"DE35 2106 0237 0050 7967 43"|         null|
		|"005400"| "33"|21060237|   51796740|"DE82 2106 0237 0051 7967 43"|         null|
		|"005400"| "33"|21060237|   52796740|"DE32 2106 0237 0052 7967 43"|         null|
		|"005400"| "33"|10060237|   53796740|"DE63 1006 0237 0053 7967 43"|         null|
		|"005400"| "33"|10060237|   54796740|"DE13 1006 0237 0054 7967 43"|         null|
		|"005400"| "33"|10060237|   55796740|"DE60 1006 0237 0055 7967 43"|         null|
		|"005400"| "33"|10060237|   56796740|"DE10 1006 0237 0056 7967 43"|         null|
		|"005400"| "33"|10060237|   57796740|"DE57 1006 0237 0057 7967 43"|         null|
		|"005400"| "33"|10060237|   58796740|"DE07 1006 0237 0058 7967 43"|         null|
		|"005400"| "33"|10060237|   59796740|"DE54 1006 0237 0059 7967 43"|         null|
		|"005400"| "33"|10060237|   60796740|"DE04 1006 0237 0060 7967 43"|         null|
		|"005400"| "33"|10060237|   61796740|"DE51 1006 0237 0061 7967 43"|         null|
		|"005400"| "33"|10060237|   62796740|"DE98 1006 0237 0062 7967 43"|         null|
		|"005400"| "33"|10060237|   63796740|"DE48 1006 0237 0063 7967 43"|         null|
		|"005400"| "33"|10060237|   64796740|"DE95 1006 0237 0064 7967 43"|         null|
		|"005400"| "33"|10060237|   65796740|"DE45 1006 0237 0065 7967 43"|         null|
		|"005400"| "33"|10060237|   66796740|"DE92 1006 0237 0066 7967 43"|         null|
		|"005400"| "33"|10060237|   67796740|"DE42 1006 0237 0067 7967 43"|         null|
		|"005400"| "33"|10060237|   68796740|"DE89 1006 0237 0068 7967 43"|         null|
		|"005400"| "33"|10060237|   69796740|"DE39 1006 0237 0069 7967 43"|         null|
		|"005400"| "33"|10060237| 1761070000|"DE55 1006 0237 0176 1070 00"|         null|
		|"005400"| "33"|10060237|2210531180L|"DE49 1006 0237 0201 0531 80"|         null|
		|"005401"| "33"|10060237|        500|"DE35 1006 0237 0000 5005 00"|         null|
		|"005401"| "33"|21060237|        502|"DE26 2106 0237 0000 5025 02"|         null|
		|"005401"| "33"|10060237|      18067|"DE20 1006 0237 0000 1806 70"|         null|
		|"005401"| "33"|10060237|     484848|"DE80 1006 0237 0000 4848 49"|         null|
		|"005401"| "33"|10060237|     760440|"DE23 1006 0237 0000 1604 40"|         null|
		|"005401"| "33"|10060237|    1018413|"DE80 1006 0237 0010 1084 13"|         null|
		|"005401"| "33"|21060237|    2601577|"DE29 2106 0237 0026 0157 76"|         null|
		|"005401"| "33"|21060237|    5005000|"DE51 2106 0237 0000 5005 00"|         null|
		|"005401"| "33"|21060237|   10796740|"DE95 2106 0237 0010 7967 43"|         null|
		|"005401"| "33"|21060237|   11796740|"DE45 2106 0237 0011 7967 43"|         null|
		|"005401"| "33"|21060237|   12796740|"DE92 2106 0237 0012 7967 43"|         null|
		|"005401"| "33"|21060237|   13796740|"DE42 2106 0237 0013 7967 43"|         null|
		|"005401"| "33"|21060237|   14796740|"DE89 2106 0237 0014 7967 43"|         null|
		|"005401"| "33"|21060237|   15796740|"DE39 2106 0237 0015 7967 43"|         null|
		|"005401"| "33"|21060237|   16307000|"DE42 2106 0237 0163 1070 00"|         null|
		|"005401"| "33"|21060237|   16610700|"DE86 2106 0237 0166 1070 00"|         null|
		|"005401"| "33"|10060237|   16796740|"DE70 1006 0237 0016 7967 43"|         null|
		|"005401"| "33"|10060237|   17796740|"DE20 1006 0237 0017 7967 43"|         null|
		|"005401"| "33"|10060237|   18796740|"DE67 1006 0237 0018 7967 43"|         null|
		|"005401"| "33"|10060237|   19796740|"DE17 1006 0237 0019 7967 43"|         null|
		|"005401"| "33"|10060237|   20796740|"DE64 1006 0237 0020 7967 43"|         null|
		|"005401"| "33"|10060237|   21796740|"DE14 1006 0237 0021 7967 43"|         null|
		|"005401"| "33"|10060237|   22796740|"DE61 1006 0237 0022 7967 43"|         null|
		|"005401"| "33"|10060237|   23796740|"DE11 1006 0237 0023 7967 43"|         null|
		|"005401"| "33"|21060237|   24796740|"DE74 2106 0237 0024 7967 43"|         null|
		|"005401"| "33"|21060237|   25796740|"DE24 2106 0237 0025 7967 43"|         null|
		|"005401"| "33"|21060237|   26610700|"DE33 2106 0237 0266 1070 00"|         null|
		|"005401"| "33"|21060237|   26796740|"DE71 2106 0237 0026 7967 43"|         null|
		|"005401"| "33"|21060237|   27796740|"DE21 2106 0237 0027 7967 43"|         null|
		|"005401"| "33"|21060237|   28796740|"DE68 2106 0237 0028 7967 43"|         null|
		|"005401"| "33"|21060237|   29796740|"DE18 2106 0237 0029 7967 43"|         null|
		|"005401"| "33"|21060237|   45796740|"DE91 2106 0237 0045 7967 43"|         null|
		|"005401"| "33"|21060237|   50796740|"DE35 2106 0237 0050 7967 43"|         null|
		|"005401"| "33"|21060237|   51796740|"DE82 2106 0237 0051 7967 43"|         null|
		|"005401"| "33"|21060237|   52796740|"DE32 2106 0237 0052 7967 43"|         null|
		|"005401"| "33"|10060237|   53796740|"DE63 1006 0237 0053 7967 43"|         null|
		|"005401"| "33"|10060237|   54796740|"DE13 1006 0237 0054 7967 43"|         null|
		|"005401"| "33"|10060237|   55796740|"DE60 1006 0237 0055 7967 43"|         null|
		|"005401"| "33"|10060237|   56796740|"DE10 1006 0237 0056 7967 43"|         null|
		|"005401"| "33"|10060237|   57796740|"DE57 1006 0237 0057 7967 43"|         null|
		|"005401"| "33"|10060237|   58796740|"DE07 1006 0237 0058 7967 43"|         null|
		|"005401"| "33"|10060237|   59796740|"DE54 1006 0237 0059 7967 43"|         null|
		|"005401"| "33"|10060237|   60796740|"DE04 1006 0237 0060 7967 43"|         null|
		|"005401"| "33"|10060237|   61796740|"DE51 1006 0237 0061 7967 43"|         null|
		|"005401"| "33"|10060237|   62796740|"DE98 1006 0237 0062 7967 43"|         null|
		|"005401"| "33"|10060237|   63796740|"DE48 1006 0237 0063 7967 43"|         null|
		|"005401"| "33"|10060237|   64796740|"DE95 1006 0237 0064 7967 43"|         null|
		|"005401"| "33"|10060237|   65796740|"DE45 1006 0237 0065 7967 43"|         null|
		|"005401"| "33"|10060237|   66796740|"DE92 1006 0237 0066 7967 43"|         null|
		|"005401"| "33"|10060237|   67796740|"DE42 1006 0237 0067 7967 43"|         null|
		|"005401"| "33"|10060237|   68796740|"DE89 1006 0237 0068 7967 43"|         null|
		|"005401"| "33"|10060237|   69796740|"DE39 1006 0237 0069 7967 43"|         null|
		|"005401"| "33"|10060237| 1761070000|"DE55 1006 0237 0176 1070 00"|         null|
		|"005401"| "33"|10060237|2210531180L|"DE49 1006 0237 0201 0531 80"|         null|		
		// BHW old 25410300, check method 09
		|"005500"| "09"|25410300|7456123400L|"DE47 2541 0200 7456 1234 00"|"BHWBDE2HXXX"|
		// Badenia Bausparkasse 25410300, check method 09
		|"005700"| "09"|50810900|7456123400L|"DE02 6601 0200 7456 1234 00"|         null|
		
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
