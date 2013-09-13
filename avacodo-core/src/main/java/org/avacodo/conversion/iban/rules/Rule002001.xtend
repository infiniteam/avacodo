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

import org.avacodo.conversion.iban.rules.Rule002000
import org.joda.time.LocalDate

package class Rule002001 extends Rule002000 {
	// changes that were communicated via email by the Bundesbank on August 30, 2013

	override accountLength7(RichIbanResult it, LocalDate date, boolean validOrig, boolean validAdd00) {

		// no more ambiguity - the 9 digit number is preferred if it is valid
		if (validAdd00) {
			overrideAccount(account.account * 100)
		}
		defaultApply("09", date);
	}

	override accountLength10(RichIbanResult it, LocalDate date) {
		// no more special rule, 10 digit accounts are rejected in apply63
	}

}
