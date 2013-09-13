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
package org.avacodo.conversion.iban.rules;

import org.hamcrest.*;
import org.jnario.lib.*;
import org.jnario.lib.Assert;
import org.jnario.runner.*;
import org.junit.*;
import org.junit.runner.*;
import org.junit.runners.*;

@Named("Rule000501")
@RunWith(JUnit4.class)
public class Rule000501Test extends Rule000500Spec {

	@Before
	public void initSubject() {
		this.subject = new Rule000501();
	}

	@Test
	@Named("iban[10045050, 930125001] should NOT throw IbanCreationImpossibleException")
	@Order(82)
	@Override
	public void _iban10045050930125001ShouldThrowIbanCreationImpossibleException() throws Exception {
		String _iban = this.iban(10045050, 930125001);
		boolean _should_endWith = Should.should_endWith(_iban, "930125001");
		Assert.assertTrue("\nExpected iban(10045050, 930125001) should endWith \"930125001\" but"
			+ "\n     iban(10045050, 930125001) is "
			+ new StringDescription().appendValue(_iban).toString() + "\n", _should_endWith);
	}

	@Test
	@Named("iban[70045050, 930125007] should NOT throw IbanCreationImpossibleException")
	@Order(84)
	@Override
	public void _iban70045050930125007ShouldThrowIbanCreationImpossibleException() throws Exception {
		String _iban = this.iban(70045050, 930125007);
		boolean _should_endWith = Should.should_endWith(_iban, "930125007");
		Assert.assertTrue("\nExpected iban(70045050, 930125007) should endWith \"930125007\" but"
			+ "\n     iban(70045050, 930125007) is "
			+ new StringDescription().appendValue(_iban).toString() + "\n", _should_endWith);
	}

}
