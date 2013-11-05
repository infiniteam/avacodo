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

import static extension java.lang.Integer.*;
import static extension java.lang.Long.*;

import java.util.Map

package class Rule000501 extends Rule000500 {
	val private static final blzExclusion522='''
		10080900 25780022 42080082 54280023 65180005 79580099
		12080000 25980027 42680081 54580020 65380003 80080000
		13080000 26080024 43080083 54680022 66280053 81080000
		14080000 26281420 44080055 55080065 66680013 82080000
		15080000 26580070 44080057 57080070 67280051 83080000
		16080000 26880063 44580070 58580074 69280035 84080000
		17080000 26981062 45080060 59080090 70080056 85080200
		18080000 28280012 46080010 60080055 70080057 86080055
		20080055 29280011 47880031 60080057 70380006 86080057
		20080057 30080055 49080025 60380002 71180005 87080000
		21080050 30080057 50080055 60480008 72180002
		21280002 31080015 50080057 61080006 73180011
		21480003 32080010 50080082 61281007 73380004
		21580000 33080030 50680002 61480001 73480013
		22180000 34080031 50780006 62080012 74180009
		22181400 34280032 50880050 62280012 74380007
		22280000 36280071 51380040 63080015 75080003
		24080000 36580072 52080080 64080014 76080053
		24180001 40080040 53080030 64380011 79080052
		25480021 41280043 54080021 65080009 79380051
	'''.toString.split(NL).map[split(" ").toList].flatten.map[parseInt].toSet
	
	val private static final Map<Pair<Integer,Long>,Long> donationMap=newImmutableMap(
			'''
		30040000 0000000036 0261103600
		47880031 0000000050 0519899900
		47840065 0000000050 0150103000
		47840065 0000000055 0150103000
		70080000 0000000094 0928553201
		70040041 0000000094 0212808000
		47840065 0000000099 0150103000
		37080040 0000000100 0269100000
		38040007 0000000100 0119160000
		37080040 0000000111 0215022000
		51080060 0000000123 0012299300
		36040039 0000000150 0161620000
		68080030 0000000202 0416520200
		30040000 0000000222 0348010002
		38040007 0000000240 0109024000
		69240075 0000000444 0445520000
		60080000 0000000502 0901581400
		60040071 0000000502 0525950200
		55040022 0000000555 0002110500
		39080005 0000000556 0204655600
		39040013 0000000556 0106555600
		57080070 0000000661 0604101200
		26580070 0000000700 0710000000
		50640015 0000000777 0222222200
		30040000 0000000999 0123799900
		86080000 0000001212 0480375900
		37040044 0000001888 0212129101
		25040066 0000001919 0141919100
		10080000 0000001987 0928127700
		50040000 0000002000 0728400300
		20080000 0000002222 0903927200
		38040007 0000003366 0385333000
		37080040 0000004004 0233533500
		37080040 0000004444 0233000300
		43080083 0000004630 0825110100
		50080000 0000006060 0096736100
		10040000 0000007878 0267878700
		10080000 0000008888 0928126501
		50080000 0000009000 0026492100
		79080052 0000009696 0300021700
		79040047 0000009696 0680210200
		39080005 0000009800 0208457000
		50080000 0000042195 0900333200
		32040024 0000047800 0155515000
		37080040 0000055555 0263602501
		38040007 0000055555 0305555500
		50080000 0000101010 0090003500
		50040000 0000101010 0311011100
		37040044 0000102030 0222344400
		86080000 0000121200 0480375900
		66280053 0000121212 0625242400
		16080000 0000123456 0012345600
		29080010 0000124124 0107502000
		37080040 0000182002 0216603302
		12080000 0000212121 4050462200
		37080040 0000300000 0983307900
		37040044 0000300000 0300000700
		37080040 0000333333 0270330000
		38040007 0000336666 0105232300
		55040022 0000343434 0002179000
		85080000 0000400000 0459488501
		37080040 0000414141 0041414100
		38040007 0000414141 0108000100
		20080000 0000505050 0500100600
		37080040 0000555666 0055566600
		20080000 0000666666 0900732500
		30080000 0000700000 0800005000
		70080000 0000700000 0750055500
		70080000 0000900000 0319966601
		37080040 0000909090 0269100000
		38040007 0000909090 0001191600
		70080000 0000949494 0575757500
		70080000 0001111111 0448060000
		70040041 0001111111 0152140000
		10080000 0001234567 0920192001
		38040007 0001555555 0258266600
		76040061 0002500000 0482146800
		16080000 0003030400 4205227110
		37080040 0005555500 0263602501
		75040062 0006008833 0600883300
		12080000 0007654321 0144000700
		70080000 0007777777 0443540000
		70040041 0007777777 0213600000
		64140036 0008907339 0890733900
		70080000 0009000000 0319966601
		61080006 0009999999 0202427500
		12080000 0012121212 4101725100
		29080010 0012412400 0107502000
		34280032 0014111935 0645753800
		38040007 0043434343 0118163500
		30080000 0070000000 0800005000
		70080000 0070000000 0750055500
		44040037 0111111111 0320565500
		70040041 0400500500 0400500500
		60080000 0500500500 0901581400
		60040071 0500500500 0512700600
	'''.toString.split(NL).map[
			val sp=split(" ")
			(sp.get(0).parseInt->sp.get(1).parseLong)->sp.get(2).parseLong
		]
	)

	override package bankCode522(){
		blzExclusion522
	}
		
	override package excluded53(int blz){
		blz==50040033
	}
	
	override package donation() {
		donationMap
	}
}
