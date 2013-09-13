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

import org.joda.time.LocalDate
import org.avacodo.model.BankConfig

package class Rule000200 extends Rule {

	override package applyTo(extension RichIbanResult it, BankConfig config, LocalDate date) {
		if((charAtEqual(7,'8') && charAtEqual(8,'6')) || charAtEqual(7,'6')){
			creationNotPossible("no iban for account suffix 86X and 6XX")
		} else{
			defaultApply(it,config.accountCheckMethod,date)
		}
	}
}