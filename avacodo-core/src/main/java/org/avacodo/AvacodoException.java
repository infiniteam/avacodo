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
package org.avacodo;

public class AvacodoException extends RuntimeException {

	private static final long serialVersionUID = 2293107670329717008L;

	public AvacodoException() {}

	public AvacodoException(String message) {
		super(message);
	}

	public AvacodoException(Throwable cause) {
		super(cause);
	}

	public AvacodoException(String message, Throwable cause) {
		super(message, cause);
	}
}
