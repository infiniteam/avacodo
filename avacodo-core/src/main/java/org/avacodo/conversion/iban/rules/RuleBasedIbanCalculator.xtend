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

import org.avacodo.conversion.iban.IbanAmbiguousException
import org.avacodo.conversion.iban.IbanCalculator
import org.avacodo.conversion.iban.IbanCreationImpossibleException
import org.avacodo.conversion.iban.IbanResult
import org.avacodo.model.BankConfig
import org.avacodo.model.BankConfigRepository
import org.avacodo.model.LegacyAccount
import javax.inject.Inject
import org.joda.time.LocalDate

final class RuleBasedIbanCalculator implements IbanCalculator{

	var BankConfigRepository repo

	@Inject
	new(BankConfigRepository repo) {
		this.repo=repo
	}

	override IbanResult getIban(LegacyAccount account){
		getIban(account, LocalDate::now)
	}

	override IbanResult getIban(LegacyAccount account, LocalDate date){
		val config=repo.getBankConfig(account.bankCode, date)
		try{
			val Rule r=Rules::from(config)
			if(r===null){
				throw new IbanCreationImpossibleException('''illegal iban rule «config.ibanRule» version «config.ibanRuleVersion»''')
			}
			val RichIbanResult iban=r.applyTo(account,config , date) as RichIbanResult
			calculateBic(iban,config,date)
			iban
		} catch(IbanAmbiguousException e){
			calculateBicAndRethrow(e, config, date)
		}
	}
	
	def private IbanResult calculateBicAndRethrow(IbanAmbiguousException exception, BankConfig config, LocalDate date) {
		val r1=exception.iban as RichIbanResult
		val r2=exception.iban2 as RichIbanResult
		calculateBic(r1, config, date)
		calculateBic(r2, config, date)
		throw new IbanAmbiguousException(r1,r2,exception)
	}


	def private calculateBic(RichIbanResult iban, BankConfig config, LocalDate date){
		if(iban.bic===null){
			if(iban.bankCodeReplaced){
				val blz=iban.account.bankCode
				val overrideConfig=repo.getBankConfig(blz, date)
				iban.bic=overrideConfig.bic
			}else{
				//iban calculator overrides bank code if a succeeding one is defined
				//hence we do not need to do that here
				iban.bic=config.bic
			}
		} else if(!iban.bicOverriddenByIbanRule){
			throw new IllegalStateException("iban rule set explicit bic, but did not mark the bic as overridden")
		}
	}
}
