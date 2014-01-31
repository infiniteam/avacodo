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

import org.avacodo.model.BankConfig
import java.util.Set
import org.joda.time.LocalDate

package class Rule001200 extends ReplaceRule {
	override replace(RichIbanResult it) {
		overrideBankCode(50050000)
	}
}

package class Rule001201 extends ReplaceRule {
	override replace(RichIbanResult it) {
		overrideBankCode(50050000)
		overrideBic("HELADEFFXXX")
	}
}

package class Rule001300 extends ReplaceRule {
	override replace(RichIbanResult it) {
		overrideBankCode(30050000)
	}
}

package class Rule001301 extends ReplaceRule {
	override replace(RichIbanResult it) {
		overrideBankCode(30050000)
		overrideBic("WELADEDDXXX")
	}
}

package class Rule001400 extends ReplaceRule {

	override replace(RichIbanResult it) {
		overrideBankCode(30060601)
		overrideBic("DAAEDEDDXXX")
	}
}

package class Rule002100 extends ReplaceRule {

	private static final Set<Integer> validLength=#{6,7,9,10}
	override replace(RichIbanResult it) {
		//condition according to errata
		if(!validLength.contains(account.accountLength)){
			creationNotPossible("allowed account lengths: 6,7,9,10, but is "+account.accountLength)
		}
		overrideBankCode(360_200_30)
		overrideBic("NBAGDE3EXXX")
	}

}

package class Rule002101 extends Rule002100 {
	// changes already implemented in Rule002100
}

package class Rule002500 extends ReplaceRule {

	override replace(RichIbanResult it) {
		overrideBankCode(600_501_01)
	}
}

package class Rule002800 extends ReplaceRule {
	override replace(RichIbanResult it) {
		if(account.bankCode==250_502_99){
			overrideBankCode(250_501_80)
			overrideBic("SPKHDE2HXXX")
		}
	}
}

package class Rule003700 extends ReplaceRule {

	override replace(RichIbanResult it) {
		if(account.bankCode==201_107_00){
			overrideBankCode(300_107_00)
			overrideBic("BOTKDEDXXXX")
		}
	}
}

package class Rule003800 extends ReplaceRule {

	private static final Set<Integer> replace=#{26691213,28591579,25090300}

	override replace(RichIbanResult it) {
		if(replace.contains(account.bankCode)){
			overrideBankCode(28590075)
			overrideBic("GENODEF1LER")
		}
	}
}

package class Rule003900 extends ReplaceRule {
	private static final Set<Integer> replace='''
		256 213 27
		265 200 17
		265 217 03
		265 223 19
		266 200 10
		266 214 13
		267 200 28
		280 210 02
		280 213 01
		280 215 04
		280 216 23
		280 217 05
		280 219 06
		280 220 15
		280 224 12
		280 225 11
		280 226 20
		280 228 22
		280 232 24
		280 233 25
		282 200 26
		282 222 08
		282 226 21
		283 200 14
		283 218 16
		284 200 07
		284 210 30
		285 200 09
		285 215 18
		291 217 31
	'''.toString.split(NL).map[Integer::parseInt(it.replaceAll(" ",""))].toSet

	override replace(RichIbanResult it) {
		if(replace.contains(account.bankCode)){
			overrideBankCode(28020050)
		}
	}
}

package class Rule004000 extends ReplaceRule {

	override replace(RichIbanResult it) {
		overrideBankCode(68052328)
		//modification according to errata
		overrideBic("SOLADES1STF")
	}
}

package class Rule004001 extends Rule004000 {
	// no changes, BIC correction already implemented in Rule004000
}

package class Rule004100 extends ReplaceRule {

	override replace(RichIbanResult it) {
		if(account.bankCode==62220000){
			overrideAccount(11404L)
			overrideBankCode(50060400)
			suppressValidation(true)
			overrideBic("GENODEFFXXX")
		}
	}
}

package class Rule004300 extends Rule {

	override package final applyTo(RichIbanResult it, BankConfig config, LocalDate date) {
		if(account.bankCode==60651070){
			overrideBankCode(66650085)
			overrideBic("PZHSDE66XXX")
		}
		// errata from 2013-08-01
		defaultApply(it, "06", date)
		it
	}
}

package class Rule004301 extends Rule004300 {
	// changes already implemented in Rule004300
}

package class Rule004500 extends ReplaceRule {

	override replace(RichIbanResult it) {
		overrideBic("ESSEDE5FXXX")
	}
}

package class Rule004501 extends Rule004500 {
	// no change required
}

package class Rule004600 extends ReplaceRule {

	override replace(RichIbanResult it) {
		overrideBankCode(31010833)
		//Santander uses BIC of the original bank code
		//rather than the replaced one
		overrideBic(config.bic)
	}
}

package class Rule004800 extends ReplaceRule {

	override replace(RichIbanResult it) {
		overrideBankCode(36010200)
		overrideBic("VONEDE33XXX")
	}
}

package class Rule005000 extends ReplaceRule {

	override replace(RichIbanResult it) {
		overrideBankCode(28550000)
		overrideBic("BRLADE21LER")
	}
}

package class Rule005500 extends ReplaceRule {

	override replace(RichIbanResult it) {
		overrideBankCode(25410200)
		overrideBic("BHWBDE2HXXX")
	}
}

package class Rule005700 extends ReplaceRule {

	override replace(RichIbanResult it) {
		if (account.bankCode == 50810900) {
			overrideBankCode(66010200)
		}
	}
}
