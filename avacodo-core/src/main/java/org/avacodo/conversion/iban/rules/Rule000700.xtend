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

import org.avacodo.conversion.iban.rules.ReplaceRule
import java.util.Map

package class Rule000700 extends ReplaceRule {

	private static final Map<Long,Long> replace='''
		111 1115 DE15370501980000001115
		221 23002157 DE25370501980023002157
		1888 18882068 DE15370501980018882068
		2006 1900668508 DE57370501981900668508
		2626 1900730100 DE41370501981900730100
		3004 1900637016 DE39370501981900637016
		3636 23002447 DE52370501980023002447
		4000 4028 DE31370501980000004028
		4444 17368 DE12370501980000017368
		5050 73999 DE83370501980000073999
		8888 1901335750 DE42370501981901335750
		30000 9992959 DE22370501980009992959
		43430 1901693331 DE56370501981901693331
		46664 1900399856 DE98370501981900399856
		55555 34407379 DE81370501980034407379
		102030 1900480466 DE17370501981900480466
		151515 57762957 DE64370501980057762957
		222222 2222222 DE85370501980002222222
		300000 9992959 DE22370501980009992959
		333333 33217 DE53370501980000033217
		414141 92817 DE83370501980000092817
		606060 91025 DE64370501980000091025
		909090 90944 DE20370501980000090944
		2602024 5602024 DE24370501980005602024
		3000000 9992959 DE22370501980009992959
		7777777 2222222 DE85370501980002222222
		8090100 38901 DE39370501980000038901
		14141414 43597665 DE96370501980043597665
		15000023 15002223 DE98370501980015002223
		15151515 57762957 DE64370501980057762957
		22222222 2222222 DE85370501980002222222
		200820082 1901783868 DE54370501981901783868
		222220022 2222222 DE85370501980002222222
	'''.toAccountAccountMap

	override replace(RichIbanResult it) {
		defaultAccountReplace(replace)
	}
}