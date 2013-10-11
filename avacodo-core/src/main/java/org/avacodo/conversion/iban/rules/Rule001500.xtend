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
package org.avacodo.conversion.iban.rules

import org.avacodo.conversion.iban.rules.ReplaceRule
import java.util.Map

package class Rule001500 extends ReplaceRule {

	// account number 94 added due to errata email from 2013-08-01
	private static final Map<Long,Long> replace='''
		556 0000101010
		888 0031870011
		4040 4003600101
		5826 1015826017
		25000 0025000110
		393393 0033013019
		444555 0032230016
		603060 6002919018
		2120041 0002130041
		80868086 4007375013
		400569017 4000569017
		94 3008888018
	'''.toAccountAccountMap

	override replace(RichIbanResult it) {
		if(account.bankCode==37060193){
			defaultAccountReplace(replace)
		}
	}
}
