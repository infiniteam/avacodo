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
package org.avacodo.conversion.iban.rules;

import org.eclipse.xtext.xbase.lib.*;
import org.jnario.lib.*;
import org.jnario.lib.Assert;
import org.jnario.runner.*;
import org.junit.*;
import org.junit.runner.*;
import org.junit.runners.*;

import org.avacodo.validation.account.*;

@Named("Rule002001")
@RunWith(JUnit4.class)
public class Rule002001Test extends Rule002000Spec {

	@Before
	public void initSubject() {
		subject = new Rule002001();
	}

	@Test
	@Named("iban[50070010,5719083, _63] should throw IbanAmbiguousException")
	@Order(8)
	@Override
	public void _iban50070010571908363ShouldThrowIbanAmbiguousException() throws Exception {
		// IbanAmbiguousException is not thrown any longer
		testAmbiguous(50070010, 5719083);
	}

	@Test
	@Named("iban[50070010,8007759, _63] should throw IbanAmbiguousException")
	@Order(9)
	@Override
	public void _iban50070010800775963ShouldThrowIbanAmbiguousException() throws Exception {
		// IbanAmbiguousException is not thrown any longer
		testAmbiguous(50070010, 8007759);
	}

	@Test
	@Named("ambiguous iban")
	@Order(10)
	@Override
	public void _ambiguousIban() throws Exception {
		// the IBAN is not ambiguous any longer
		testAmbiguous(50070010, 5719083);
	}

	@Test
	@Named("uncertain iban 1")
	@Order(11)
	@Override
	public void _uncertainIban1() throws Exception {
		testUncertain(50070010, 1415900000L);
	}

	@Test
	@Named("uncertain iban 2")
	@Order(12)
	@Override
	public void _uncertainIban2() throws Exception {
		testUncertain(50070010, 4287900000L);
	}

	@Test
	@Named("uncertain iban 3")
	@Order(13)
	@Override
	public void _uncertainIban3() throws Exception {
		testUncertain(50070010, 4915518000L);
	}

	@Test
	@Named("uncertain iban 4")
	@Order(14)
	@Override
	public void _uncertainIban4() throws Exception {
		testUncertain(50070010, 5814488000L);
	}

	@Test
	@Named("uncertain iban 5")
	@Order(15)
	@Override
	public void _uncertainIban5() throws Exception {
		testUncertain(50070010, 3805868000L);
	}

	private void testAmbiguous(final int bankCode, final long account) {
		final String _iban = this.iban(bankCode, account, Rule002000Spec._63);
		// 00 should have been appended to the account number   
		boolean _should_endWith_1 = Should.should_endWith(_iban, account + "00");
		Assert.assertTrue("\nExpected iban should endWith \"" + account + "00\" but"
			+ "\n     iban is " + _iban,
			_should_endWith_1);
	}

	private void testUncertain(final int bankCode, final long account) {
		try {
			this.iban(bankCode, account, Rule002000Spec._63);
			org.junit.Assert.fail();
		} catch (final Throwable _t) {
			if (_t instanceof AccountValidationException) {
				// expected
			} else {
				throw Exceptions.sneakyThrow(_t);
			}
		}
	}

}
