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

import java.util.Set
import org.joda.time.LocalDate
import org.avacodo.model.BankConfig

package class Rule003000 extends Rule {


	private static final Set<Long> excludeValidation='''
		1718190 22000225 49902271 49902280 101680029
		104200028 106200025 108000171 108000279 108001364
		108001801 108002514 300008542 9130099995 9130500002
		9131100008 9131600000 9131610006 9132200006 9132400005
		9132600004 9132700017 9132700025 9132700033 9132700041
		9133200700 9133200735 9133200743 9133200751 9133200786
		9133200808 9133200816 9133200824 9133200832 9136700003
		9177300010 9177300060 9198100002 9198200007 9198200104
		9198300001 9331300141 9331300150 9331401010 9331401061
		9349010000 9349100000 9360500001 9364902007 9366101001
		9366104000 9370620030 9370620080 9371900010 9373600005
		9402900021 9605110000 9614001000 9615000016 9615010003
		9618500036 9631020000 9632600051 9632600060 9635000012
		9635000020 9635701002 9636010003 9636013002 9636016001
		9636018004 9636019000 9636022001 9636024004 9636025000
		9636027003 9636028000 9636045001 9636048000 9636051001
		9636053004 9636120003 9636140004 9636150000 9636320002
		9636700000 9638120000 9639401100 9639801001 9670010004
		9680610000 9705010002 9705403004 9705404000 9705509996
		9707901001 9736010000 9780100050 9791000030 9990001003
		9990001100 9990002000 9990004002 9991020001 9991040002
		9991060003 9999999993 9999999994 9999999995 9999999996
		9999999997 9999999998 9999999999
	'''.asLongSet

	
	def static private asLongSet(CharSequence sequence) {
		sequence.toString.split("\n").map[split(' ').toList].flatten.map[Long::parseLong(it.trim)].toSet
	}
	
	override applyTo(RichIbanResult result, BankConfig config, LocalDate date) {
		if(result.account.bankCode==13091054 && excludeValidation.contains(result.account.account)){
			result.suppressValidation(true)
		}
		defaultApply(result,config.accountCheckMethod,date)
	}
}