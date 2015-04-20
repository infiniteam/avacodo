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
import org.avacodo.model.BankConfigRepository
import org.joda.time.LocalDate

class SimpleRulesHelper {

	def static config(String checkM, String iban){
		BankConfig::builder.checkMethod(checkM).ibanRule(iban).bic("ignore").build
	}

	def static String getTrimmed(String string) {
		if(string===null) null else string.replaceAll(" ","")
	}

	def static RuleBasedIbanCalculator getCalculator(BankConfig config){
		new RuleBasedIbanCalculator(new SinleConfigRepo(config))
	}
}

@Data
class SinleConfigRepo implements BankConfigRepository{
	BankConfig config
	
	override getBankConfig(int bankCode) {
		config
	}
	
	override getBankConfig(int bankCode, LocalDate date) {
		bankCode.bankConfig
	}
}
