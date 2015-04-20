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
package org.avacodo.validation.account;

import org.joda.time.*;

public interface AccountValidator {

	AccountValidator defImpl=new AccountValidator() {
		@Override
		public boolean checkAccountNumber(long accountNumber, String checkMethod,
				int blz, LocalDate date) {
			return BankAccountValidator.checkAccountNumber(accountNumber, checkMethod,blz,date);
		}

		@Override
		public boolean checkAccountNumber(long accountNumber, String checkMethod,
				int blz) {
			return BankAccountValidator.checkAccountNumber(accountNumber, checkMethod,blz);
		}
	};

    /**
     * Check an account number of a German bank account.
     * @param account number
     * @param check method identifier
     * @param blz, bank code
     */
    boolean checkAccountNumber(long accountNumber, String checkMethod, int blz);

    /**
     * Check an account number of a German bank account.
     * @param account number
     * @param check method identifier
     * @param blz, bank code
     * @param date indicating which version of the method is to be used
     */
    boolean checkAccountNumber(long accountNumber, String checkMethod, int blz, LocalDate date);

}
