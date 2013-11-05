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
import org.avacodo.model.DeIban
import org.avacodo.model.LegacyAccount
import org.avacodo.validation.account.AccountValidationException

import static extension org.avacodo.conversion.iban.rules.DoubleChecker.*
import static extension org.avacodo.conversion.iban.rules.SimpleRulesHelper.*

describe RuleBasedIbanCalculator "SimpleRules1"{

	//check method 09 -no validation
	//iban=checksum - account validation error expected
	def{
		|    rule|check|     blz|      konto|                         Iban|          Bic|
		//default (replaced bank code)
		|"000000"| "09"|10030500|  532013018|"DE15 1003 0500 0532 0130 18"|         null|
		//no iban calculation
		|"000100"| "09"|10030500|  532013018|                         null|         null|
		//Augsburger Aktienbank 72020700, check method 23
		|"000200"| "09"|72020700|          1|"DE59 7202 0700 0000 0000 01"|         null|
		|"000200"| "09"|72020700|        864|                         null|         null|
		|"000200"| "09"|72020700|     123860|                         null|         null|
		|"000200"| "09"|72020700|     123603|                         null|         null|
		//Aareal Bank Zw Wiesbaden 51010800, check method 09
		//TODO account number replaced for all banks using this rule
		|"000300"| "09"|51010800|6161604670L|                         null|         null|
		//Landesbank Berlin 10050000, check method B8
		|"000400"| "B8"|10050000|        135|"DE86 1005 0000 0990 0214 40"|         null|
		|"000400"| "B8"|10050000|       1111|"DE19 1005 0000 6600 0120 20"|         null|
		|"000400"| "B8"|10050000|       1900|"DE73 1005 0000 0920 0190 05"|         null|
		|"000400"| "B8"|10050000|       7878|"DE48 1005 0000 0780 0080 06"|         null|
		|"000400"| "B8"|10050000|       8888|"DE43 1005 0000 0250 0309 42"|         null|
		|"000400"| "B8"|10050000|       9595|"DE60 1005 0000 1653 5247 03"|         null|
		|"000400"| "B8"|10050000|      97097|"DE15 1005 0000 0013 0441 50"|         null|
		|"000400"| "B8"|10050000|     112233|"DE54 1005 0000 0630 0258 19"|         null|
		|"000400"| "B8"|10050000|     336666|"DE22 1005 0000 6604 0589 03"|         null|
		|"000400"| "B8"|10050000|     484848|"DE43 1005 0000 0920 0189 63"|         null|
		//Stadtsparkasse München 70150000, check method 00
		|"000600"| "00"|70150000|    1111111|"DE62 7015 0000 0020 2288 88"|         null|
		|"000600"| "00"|70150000|    7777777|"DE48 7015 0000 0903 2860 03"|         null|
		|"000600"| "00"|70150000|   34343434|"DE30 7015 0000 1000 5065 17"|         null|
		|"000600"| "00"|70150000|      70000|"DE64 7015 0000 0018 1800 18"|         null|
		//Sparkasse KölnBonn 37050198, check method 00
		|"000700"| "00"|37050198|        111|"DE15 3705 0198 0000 0011 15"|         null|
		|"000700"| "00"|37050198|        221|"DE25 3705 0198 0023 0021 57"|         null|
		|"000700"| "00"|37050198|       1888|"DE15 3705 0198 0018 8820 68"|         null|
		|"000700"| "00"|37050198|       2006|"DE57 3705 0198 1900 6685 08"|         null|
		|"000700"| "00"|37050198|       2626|"DE41 3705 0198 1900 7301 00"|         null|
		|"000700"| "00"|37050198|       3004|"DE39 3705 0198 1900 6370 16"|         null|
		|"000700"| "00"|37050198|       3636|"DE52 3705 0198 0023 0024 47"|         null|
		|"000700"| "00"|37050198|       4000|"DE31 3705 0198 0000 0040 28"|         null|
		|"000700"| "00"|37050198|       4444|"DE12 3705 0198 0000 0173 68"|         null|
		|"000700"| "00"|37050198|       5050|"DE83 3705 0198 0000 0739 99"|         null|
		|"000700"| "00"|37050198|       8888|"DE42 3705 0198 1901 3357 50"|         null|
		|"000700"| "00"|37050198|      30000|"DE22 3705 0198 0009 9929 59"|         null|
		|"000700"| "00"|37050198|      43430|"DE56 3705 0198 1901 6933 31"|         null|
		|"000700"| "00"|37050198|      46664|"DE98 3705 0198 1900 3998 56"|         null|
		|"000700"| "00"|37050198|      55555|"DE81 3705 0198 0034 4073 79"|         null|
		|"000700"| "00"|37050198|     102030|"DE17 3705 0198 1900 4804 66"|         null|
		|"000700"| "00"|37050198|     151515|"DE64 3705 0198 0057 7629 57"|         null|
		|"000700"| "00"|37050198|     222222|"DE85 3705 0198 0002 2222 22"|         null|
		|"000700"| "00"|37050198|     300000|"DE22 3705 0198 0009 9929 59"|         null|
		|"000700"| "00"|37050198|     333333|"DE53 3705 0198 0000 0332 17"|         null|
		|"000700"| "00"|37050198|     414141|"DE83 3705 0198 0000 0928 17"|         null|
		|"000700"| "00"|37050198|     606060|"DE64 3705 0198 0000 0910 25"|         null|
		|"000700"| "00"|37050198|     909090|"DE20 3705 0198 0000 0909 44"|         null|
		|"000700"| "00"|37050198|    2602024|"DE24 3705 0198 0005 6020 24"|         null|
		|"000700"| "00"|37050198|    3000000|"DE22 3705 0198 0009 9929 59"|         null|
		|"000700"| "00"|37050198|    7777777|"DE85 3705 0198 0002 2222 22"|         null|
		|"000700"| "00"|37050198|    8090100|"DE39 3705 0198 0000 0389 01"|         null|
		|"000700"| "00"|37050198|   14141414|"DE96 3705 0198 0043 5976 65"|         null|
		|"000700"| "00"|37050198|   15000023|"DE98 3705 0198 0015 0022 23"|         null|
		|"000700"| "00"|37050198|   15151515|"DE64 3705 0198 0057 7629 57"|         null|
		|"000700"| "00"|37050198|   22222222|"DE85 3705 0198 0002 2222 22"|         null|
		|"000700"| "00"|37050198|  200820082|"DE54 3705 0198 1901 7838 68"|         null|
		|"000700"| "00"|37050198|  222220022|"DE85 3705 0198 0002 2222 22"|         null|
		//BHF-Bank 10 BLZs, check method 60
		|"000800"| "60"|50020200|      38000|"DE80 5002 0200 0000 0380 00"|"BHFBDEFF500"|
		|"000800"| "60"|51020000|   30009963|"DE46 5002 0200 0030 0099 63"|"BHFBDEFF500"|
		|"000800"| "60"|30020500|   40033086|"DE02 5002 0200 0040 0330 86"|"BHFBDEFF500"|
		|"000800"| "60"|20120200|   50017409|"DE55 5002 0200 0050 0174 09"|"BHFBDEFF500"|
		|"000800"| "60"|70220200|   55036107|"DE38 5002 0200 0055 0361 07"|"BHFBDEFF500"|
		|"000800"| "60"|10020200|   70049754|"DE98 5002 0200 0070 0497 54"|"BHFBDEFF500"|
		//Sparkasse Schopfheim-Zell 68351557, check method 00 (Wiesental 68351976)
		|"000900"| "00"|68351976| 1116232594|"DE03 6835 1557 3047 2325 94"|"SOLADES1SFH"|
		|"000900"| "00"|68351976|   16005845|"DE10 6835 1557 0016 0058 45"|"SOLADES1SFH"|
		//Frankfurter Sparkasse 50050201, check method 96
		|"001000"| "96"|50050201|       2000|"DE42 5005 0201 0000 2220 00"|         null|
		|"001000"| "96"|50050201|     800000|"DE89 5005 0201 0000 1808 02"|         null|
		|"001000"| "96"|50050222|9421000009L|"DE54 5005 0201 9421 0000 09"|         null|
		|"001001"| "96"|50050201|       2000|"DE42 5005 0201 0000 2220 00"|         null|
		|"001001"| "96"|50050201|     800000|"DE89 5005 0201 0000 1808 02"|         null|
		|"001001"| "96"|50050222|9421000009L|"DE54 5005 0201 9421 0000 09"|         null|
		//Sparkasse Krefeld 32050000, check method 00
		|"001100"| "00"|32050000|       1000|"DE44 3205 0000 0008 0100 01"|         null|
		|"001100"| "00"|32050000|      47800|"DE36 3205 0000 0000 0478 03"|         null|
		//Landesbank Hessen-Thüringen 50050000 50850049, check method 00
		|"001200"| "00"|50850049|5000002096L|"DE95 5005 0000 5000 0020 96"|         null|
		|"001201"| "00"|50850049|5000002096L|"DE95 5005 0000 5000 0020 96"|"HELADEFFXXX"|
		//Landesbank Hessen-Thüringen (Düsseldorf?) 30050000 and others, check method 0, 09!!!
		|"001300"| "08"|40050000|      60624|"DE15 3005 0000 0000 0606 24"|         null|
		|"001301"| "08"|40050000|      60624|"DE15 3005 0000 0000 0606 24"|"WELADEDDXXX"|
		//Deutsche Apotheker... (apoBank) 30060601 und many others, check method A4
		|"001400"| "A4"|99999999|          1|"DE68 3006 0601 0000 0000 01"|"DAAEDEDDXXX"|
		//Pax-Bank 37060193 and many others, check method 06
		|"001500"| "06"|37060193|        556|"DE75 3706 0193 0000 1010 10"|         null|
		|"001500"| "06"|37060193|        888|"DE94 3706 0193 0031 8700 11"|         null|
		|"001500"| "06"|37060193|       4040|"DE13 3706 0193 4003 6001 01"|         null|
		|"001500"| "06"|37060193|       5826|"DE49 3706 0193 1015 8260 17"|         null|
		|"001500"| "06"|37060193|      25000|"DE44 3706 0193 0025 0001 10"|         null|
		|"001500"| "06"|37060193|     393393|"DE10 3706 0193 0033 0130 19"|         null|
		|"001500"| "06"|37060193|     444555|"DE38 3706 0193 0032 2300 16"|         null|
		|"001500"| "06"|37060193|     603060|"DE98 3706 0193 6002 9190 18"|         null|
		|"001500"| "06"|37060193|    2120041|"DE92 3706 0193 0002 1300 41"|         null|
		|"001500"| "06"|37060193|   80868086|"DE42 3706 0193 4007 3750 13"|         null|
		|"001500"| "06"|37060193|  400569017|"DE90 3706 0193 4000 5690 17"|         null|
		|"001500"| "06"|37060193|         94|"DE17 3706 0193 3008 8880 18"|         null|
		|"001501"| "06"|37060193|        556|"DE75 3706 0193 0000 1010 10"|         null|
		|"001501"| "06"|37060193|        888|"DE94 3706 0193 0031 8700 11"|         null|
		|"001501"| "06"|37060193|       4040|"DE13 3706 0193 4003 6001 01"|         null|
		|"001501"| "06"|37060193|       5826|"DE49 3706 0193 1015 8260 17"|         null|
		|"001501"| "06"|37060193|      25000|"DE44 3706 0193 0025 0001 10"|         null|
		|"001501"| "06"|37060193|     393393|"DE10 3706 0193 0033 0130 19"|         null|
		|"001501"| "06"|37060193|     444555|"DE38 3706 0193 0032 2300 16"|         null|
		|"001501"| "06"|37060193|     603060|"DE98 3706 0193 6002 9190 18"|         null|
		|"001501"| "06"|37060193|    2120041|"DE92 3706 0193 0002 1300 41"|         null|
		|"001501"| "06"|37060193|   80868086|"DE42 3706 0193 4007 3750 13"|         null|
		|"001501"| "06"|37060193|  400569017|"DE90 3706 0193 4000 5690 17"|         null|
		|"001501"| "06"|37060193|         94|"DE17 3706 0193 3008 8880 18"|         null|
		//Kölner Bank 37160087 , check method 06
		|"001600"| "06"|37160087|     300000|"DE68 3716 0087 0018 1280 12"|         null|
		//Volksbank Bonn Rhein-Sieg 38060186 , check method 06
		|"001700"| "06"|38060186|        100|"DE43 3806 0186 2009 0900 13"|         null|
		|"001700"| "06"|38060186|        111|"DE38 3806 0186 2111 1110 17"|         null|
		|"001700"| "06"|38060186|        240|"DE77 3806 0186 2100 2400 10"|         null|
		|"001700"| "06"|38060186|       4004|"DE23 3806 0186 2204 0040 16"|         null|
		|"001700"| "06"|38060186|       4444|"DE04 3806 0186 2044 4440 14"|         null|
		|"001700"| "06"|38060186|       6060|"DE07 3806 0186 2016 0600 14"|         null|
		|"001700"| "06"|38060186|     102030|"DE16 3806 0186 1102 0300 16"|         null|
		|"001700"| "06"|38060186|     333333|"DE06 3806 0186 2033 3330 16"|         null|
		|"001700"| "06"|38060186|     909090|"DE43 3806 0186 2009 0900 13"|         null|
		|"001700"| "06"|38060186|   50005000|"DE95 3806 0186 5000 5000 13"|         null|
		//Aachener Bank 39060180 , check method 06
		|"001800"| "06"|39060180|        556|"DE05 3906 0180 0120 4401 10"|         null|
		|"001800"| "06"|39060180|5435435430L|"DE37 3906 0180 0543 5435 43"|         null|
		|"001800"| "06"|39060180|       2157|"DE07 3906 0180 0121 7870 16"|         null|
		|"001800"| "06"|39060180|       9800|"DE19 3906 0180 0120 8000 19"|         null|
		|"001800"| "06"|39060180|     202050|"DE61 3906 0180 1221 8640 14"|         null|
		//Bethmann Bank 50120383, 70030800, 50220200, 50130100, check method D9
		|"001900"| "D9"|70030800|   20475000|"DE82 5012 0383 0020 4750 00"|"DELBDE33XXX"|
		|"001900"| "D9"|50220200|   20475000|"DE82 5012 0383 0020 4750 00"|"DELBDE33XXX"|
		|"001900"| "D9"|50130100|   20475000|"DE82 5012 0383 0020 4750 00"|"DELBDE33XXX"|
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
