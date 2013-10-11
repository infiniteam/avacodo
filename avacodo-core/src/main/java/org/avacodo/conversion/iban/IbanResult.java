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
package org.avacodo.conversion.iban;

public interface IbanResult {
	String getIban();
	String getBic();
	/**
	 * indicates whether account number has been replaced by a different one during iban calculation
	 * */
	boolean isAccountReplaced();
	/**
	 * indicates whether the bank code has been replaced by a different one during iban calculation
	 * */
	boolean isBankCodeReplaced();
	/**
	 * indicates whether the iban rule requires a particular bic to be used, regardless of
	 * what the bic in the BankConfig would be
	 * */
	boolean isBicOverriddenByIbanRule();
}
