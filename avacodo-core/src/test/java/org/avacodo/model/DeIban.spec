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
package org.avacodo.model

describe DeIban {

	fact from(76020070,1500000100) should be "DE49760200701500000100"

	fact validate("DE49760200701500000100") should be true
	fact validate("DE48760200701500000100") should be false

	fact account("DE48760200701500000100").account should be 1500000100L
	fact account("DE48760200701500000100").bankCode should be 76020070

	def account(String iban){
		DeIban::accountFromIban(iban)
	}
	def from(int blz, long konto){
		DeIban::from(new LegacyAccount(blz, konto))
	}
	def validate(String iban){
		DeIban::validate(iban)
	}
}
