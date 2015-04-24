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
package org.avacodo.model;

import org.joda.time.LocalDate;
public interface BankConfigRepository {

	/**
	 * must not return null
	 * must not return bank config, if the bank code is deleted
	 * 
	 * @throws UnknownBankCodeException
	 * */
	BankConfig getBankConfig(int bankCode);

	/**
	 * must not return null
	 * must not return bank config, if the bank code is deleted
	 * will be replaced by a method that uses java.time.LocalData
	 * 
	 * @throws UnknownBankCodeException
	 * */
	@Deprecated
	BankConfig getBankConfig(int bankCode, LocalDate date);
}
