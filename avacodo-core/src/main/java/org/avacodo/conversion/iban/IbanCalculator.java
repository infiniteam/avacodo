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
package org.avacodo.conversion.iban;

import org.avacodo.model.*;
import org.joda.time.*;

/**
 * default implementation is RuleBasedIbanCalculator
 * */
public interface IbanCalculator {
	/**
	 * throws an AvocadoException if (unambiguous) iban creation failed
	 * (e.g. bank code unknown, account validation failed, ...)
	 * */
	IbanResult getIban(LegacyAccount account);

	/**
	 * throws an AvocadoException if (unambiguous) iban creation failed
	 * (e.g. bank code unknown, account validation failed, ...)
	 * */
	IbanResult getIban(LegacyAccount account, LocalDate date);
}
