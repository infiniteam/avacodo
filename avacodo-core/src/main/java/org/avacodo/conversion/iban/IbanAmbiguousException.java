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


/**
 * To be thrown if two IBANs are possible.
 */
public class IbanAmbiguousException extends IbanUncertainException {

	/**
	 * version number for serialization
	 */
	private static final long serialVersionUID = -5150430969759012466L;
	private IbanResult r2;

	public IbanAmbiguousException(final IbanResult r1, final IbanResult r2, final Throwable cause) {
		super(r1, cause);
		this.r2 = r2;
	}

	public IbanAmbiguousException(final IbanResult r1, final IbanResult r2, final String message) {
		super(r1, message);
		this.r2 = r2;
	}

	public IbanResult getIban2() {
		return r2;
	}
}
