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

import org.avacodo.conversion.iban.IbanResult
import org.avacodo.model.BankConfig
import org.avacodo.model.LegacyAccount
import java.util.Map
import org.eclipse.xtext.xbase.lib.Pair
import org.joda.time.LocalDate

import static extension java.lang.Integer.*
import static extension java.lang.Long.*

package class Rule000500 extends Rule {
	//validation rule 76 explicitly reqires the basic account to have 5, 6 or 7 digits
	//however most online validators accept accounts like 23 or 24
	//iban calcualtion is successful as well
	//ckonto (netslave) was in contact with Commerzbank and will adapt its implementation taking
	//the length restriction into account

	//the following case is still ambiguous
	//checking a 7-digit number may be successful with and without appending 00
	//in this case we assume, that appending 00 is not necessary


	val private static final blzExclusion522='''
		10080900 25780022 42080082 53080030 64380011 79080052
		12080000 25980027 42680081 54080021 65080009 79380051
		13080000 26080024 43080083 54280023 65180005 79580099
		14080000 26281420 44080055 54580020 65380003 80080000
		15080000 26580070 44080057 54680022 66280053 81080000
		16080000 26880063 44580070 55080065 66680013 82080000
		17080000 26981062 45080060 57080070 67280051 83080000
		18080000 28280012 46080010 58580074 69280035 84080000
		20080055 29280011 47880031 59080090 70080056 85080200
		20080057 30080055 49080025 60080055 70080057 86080055
		21080050 30080057 50080055 60080057 70380006 86080057
		21280002 31080015 50080057 60380002 71180005 87080000
		21480003 32080010 50080081 60480008 72180002
		21580000 33080030 50080082 61080006 73180011
		22180000 34080031 50680002 61281007 73380004
		22181400 34280032 50780006 61480001 73480013
		22280000 36280071 50880050 62080012 74180009
		24080000 36580072 51080000 62280012 74380007
		24180001 40080040 51380040 63080015 75080003
		25480021 41280043 52080080 64080014 76080053
	'''.toString.split(NL).map[split(" ").toList].flatten.map[parseInt].toSet

	val private static final Map<Pair<Integer,Long>,Long> donationMap=newImmutableMap(
			'''
		30040000 0000000036 0002611036
		47880031 0000000050 0519899900
		47840065 0000000050 0001501030
		47840065 0000000055 0001501030
		70080000 0000000094 0928553201
		70040041 0000000094 0002128080
		47840065 0000000099 0001501030
		37080040 0000000100 0269100000
		38040007 0000000100 0001191600
		37080040 0000000111 0215022000
		51080060 0000000123 0012299300
		36040039 0000000150 0001616200
		68080030 0000000202 0416520200
		30040000 0000000222 0348010002
		38040007 0000000240 0001090240
		69240075 0000000444 0004455200
		60080000 0000000502 0901581400
		60040071 0000000502 0005259502
		55040022 0000000555 0002110500
		39080005 0000000556 0204655600
		39040013 0000000556 0001065556
		57080070 0000000661 0604101200
		26580070 0000000700 0710000000
		50640015 0000000777 0002222222
		30040000 0000000999 0001237999
		86080000 0000001212 0480375900
		37040044 0000001888 0212129101
		25040066 0000001919 0001419191
		10080000 0000001987 0928127700
		50040000 0000002000 0007284003
		20080000 0000002222 0903927200
		38040007 0000003366 0003853330
		37080040 0000004004 0233533500
		37080040 0000004444 0233000300
		43080083 0000004630 0825110100
		50080000 0000006060 0096736100
		10040000 0000007878 0002678787
		10080000 0000008888 0928126501
		50080000 0000009000 0026492100
		79080052 0000009696 0300021700
		79040047 0000009696 0006802102
		39080005 0000009800 0208457000
		50080000 0000042195 0900333200
		32040024 0000047800 0001555150
		37080040 0000055555 0263602501
		38040007 0000055555 0003055555
		50080000 0000101010 0090003500
		50040000 0000101010 0003110111
		37040044 0000102030 0002223444
		86080000 0000121200 0480375900
		66280053 0000121212 0625242400
		16080000 0000123456 0012345600
		29080010 0000124124 0107502000
		37080040 0000182002 0216603302
		12080000 0000212121 4050462200
		37080040 0000300000 0983307900
		37040044 0000300000 0003000007
		37080040 0000333333 0270330000
		38040007 0000336666 0001052323
		55040022 0000343434 0002179000
		85080000 0000400000 0459488501
		37080040 0000414141 0041414100
		38040007 0000414141 0001080001
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
		70040041 0001111111 0001521400
		10080000 0001234567 0920192001
		38040007 0001555555 0002582666
		76040061 0002500000 0004821468
		16080000 0003030400 4205227110
		37080040 0005555500 0263602501
		75040062 0006008833 0600883300
		12080000 0007654321 0144000700
		70080000 0007777777 0443540000
		70040041 0007777777 0002136000
		64140036 0008907339 0890733900
		70080000 0009000000 0319966601
		61080006 0009999999 0202427500
		12080000 0012121212 4101725100
		29080010 0012412400 0107502000
		34280032 0014111935 0645753800
		38040007 0043434343 0001181635
		30080000 0070000000 0800005000
		70080000 0070000000 0750055500
		44040037 0111111111 0003205655
		70040041 0400500500 0004005005
		60080000 0500500500 0901581400
		60040071 0500500500 0005127006
	'''.toString.split(NL).map[
			val sp=split(" ")
			(sp.get(0).parseInt->sp.get(1).parseLong)->sp.get(2).parseLong
		]
	)


	
	override package applyTo(extension RichIbanResult it, BankConfig config, LocalDate date) {
		val checkMethod=config.accountCheckMethod
		replaceDonation
		//account must be valid at all
		validateWith(validator,checkMethod,date)

		if( account.bankCode.excluded53 ){
			creationNotPossible("rule excludes bank code "+account.bankCode)
		}else if(account.excluded522){
			creationNotPossible("rule excludes account range for bank code "+account.bankCode)
		} else{
			if(account.bankCode.group4){
				overrideBic("COBADEFFXXX")
			}
			switch checkMethod{
				case "13":apply13(date)
				case "76":apply76(date)
				default: defaultApply(it,checkMethod,date)
			}
		}
	}

	def private IbanResult apply13(RichIbanResult it, LocalDate date){
		val length=account.accountLength
		//according to spec always append 00 to 6/7 digit accounts 
		if(length==6 || length==7){
			validateWith(getValidator(),"13",date)
			suppressValidation(true)
			overrideAccount(account.account*100)
		}
		defaultApply(it,"13",date)
	}

	def private IbanResult apply76(RichIbanResult it, LocalDate date){
		var acc=account.paddedAccount
		val orig=check76(acc.substring(1,8))
		val add00=canBeMoved76 && check76(acc.substring(3,10))

		if(orig&&!add00){
			//everything is fine
		} else if(!orig && add00){
			overrideAccount(account.account*100)
		} else if(orig && add00){
			// potential ambiguity for account length 7 
			//(=5 digits main account, so adding 00 makes it a valid 7 digit main account)
			//in this case, we do NOT append 00
			val accLength=account.accountLength
			if(accLength<7){
				//make main account number long enough
				overrideAccount(account.account*100)
			}
		}
		defaultApply(it,"76",date)
	}

	/**
	 * our interpretation: account type must be 0, otherwise adding 00 to the end is not possible at all 
	 */
	def package canBeMoved76(RichIbanResult it){
		account.accountLength<9
	}

	private static final int[] weight=#[7,6,5,4,3,2]

	def package final boolean check76(String stammNummer){
		var Integer sum=(0..<weight.size).fold(0)[int p1, int index| 
			val product =weight.get(index)*(stammNummer.charAt(index)-'0')
			p1+product
		]
		val int checkSum=sum%11
		stammNummer.charAt(6)-'0'==checkSum
	}


	def package donation() {
		donationMap
	}

	def package replaceDonation(RichIbanResult it){
		val replace=donation.get(account.bankCode->account.account)
		if(replace!==null){
			overrideAccount(replace)
		}
	}

	def package excluded522(LegacyAccount it){
		 bankCode522.contains(bankCode) && (account>=998_000_000) && (account<=999_499_999)
	}
	
	def package bankCode522(){
		blzExclusion522
	}

	def package excluded53(int blz){
		blz==10045050 || blz==50040033 ||blz==70045050
	}
	def package group4(Integer blz){
		blz.toString.charAt(3).charEqual('4')
	}
}