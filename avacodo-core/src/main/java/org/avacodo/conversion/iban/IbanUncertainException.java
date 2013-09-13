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
package org.avacodo.conversion.iban;

import org.avacodo.*;

/**
 * To be thrown if an IBAN can be calculated but is possibly wrong.
 */
public class IbanUncertainException extends AvacodoException {

	/**
	 * version number for serialization
	 */
	private static final long serialVersionUID = -7243482511426018159L;
	private IbanResult r1;

	public IbanUncertainException(final IbanResult r1, final Throwable cause) {
		super(cause);
		this.r1 = r1;
	}

	public IbanUncertainException(final IbanResult r1, final String message) {
		super(message);
		this.r1 = r1;
	}

	public IbanResult getIban() {
		return r1;
	}
}
