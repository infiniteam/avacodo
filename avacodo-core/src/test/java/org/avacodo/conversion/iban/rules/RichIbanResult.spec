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

import org.avacodo.model.BankConfig
import org.avacodo.model.LegacyAccount
import org.avacodo.validation.account.AccountValidationException
import org.joda.time.LocalDate

describe RichIbanResult {
/**TODO!!!! */
	before{
		subject=new RichIbanResult(new LegacyAccount(12345678,1234567890L), 
			BankConfig::builder
			.checkMethod("00")
			.bic("BIC")
			.ibanRule("000110")
			.succeedingBlz(10000000)
			.build
		)
	}

	fact subject.account.account should be 1234567890L
	fact subject.account.bankCode should be 12345678
	fact subject.accountReplaced should be false
	fact subject.bankCodeReplaced should be false
	fact subject.bicOverriddenByIbanRule should be false

	//overriding by the same account should not have any impact
	//overriding by different account should set the corresponding marker
	fact "override account"{
		subject.overrideAccount(1234567890L)
		subject.accountReplaced should be false
		subject.account.account should be 1234567890L

		subject.overrideAccount(1L)
		subject.accountReplaced should be true
		subject.account.account should be 1L
		subject.bankCodeReplaced should be false
		subject.account.bankCode should be 12345678
		subject.bicOverriddenByIbanRule should be false
	}

	//overriding by the same bankcode should not have any impact
	//overriding by different bankcode should set the corresponding marker
	fact "override bankcode"{
		subject.overrideBankCode(null)
		subject.bankCodeReplaced should be false
		subject.account.bankCode should be 12345678

		subject.overrideBankCode(12345678)
		subject.bankCodeReplaced should be false
		subject.account.bankCode should be 12345678

		subject.overrideBankCode(87654321)
		subject.bankCodeReplaced should be true
		subject.account.bankCode should be 87654321
		subject.accountReplaced should be false
		subject.account.account should be 1234567890L
		subject.bicOverriddenByIbanRule should be false
	}

	//setting the bic should not mark the bic as overridden
	fact "setting bic"{
		subject.bic should be null as String
		subject.bicOverriddenByIbanRule should be false
		subject.setBic("TADA")
		subject.bic should be "TADA"
		subject.bicOverriddenByIbanRule should be false
	}

	//overriding by a different bic should mark the bic as overridden
	fact "overriding bic"{
		subject.bic should be null as String
		subject.bicOverriddenByIbanRule should be false

		subject.overrideBic(null)
		subject.bic should be null as String
		subject.bicOverriddenByIbanRule should be false

		//hack for setting bic to something different, so that overriding has no effect
		subject.setBic("TIDUM")
		subject.overrideBic("TIDUM")
		subject.bic should be "TIDUM"
		subject.bicOverriddenByIbanRule should be false

		//overriding by a different value
		subject.overrideBic("TADA")
		subject.bic should be "TADA"
		subject.bicOverriddenByIbanRule should be true
	}

	fact "validating"{
		subject.validateWith(new FailingValidator, "00",LocalDate::now) should throw AccountValidationException
		subject.suppressValidation(true)
		subject.validateWith(new FailingValidator, "00",LocalDate::now) //should not throw account validation exception
	}

	fact "iban calculation"{
		subject.iban should be null as String
		subject.bankCodeReplaced should be false
		subject.initDefaultIban
		//use succeeding blz
		subject.iban should be "DE23100000001234567890"
		subject.bankCodeReplaced should be true
	}
}
