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
package org.avacodo.filebased

import org.junit.Ignore
import org.junit.Test
import org.junit.Assert
import com.google.common.base.Charsets
import java.io.FileNotFoundException
import java.io.File
import java.nio.charset.UnsupportedCharsetException
import org.avacodo.model.LegacyAccount

class InvokeConverter{

	//TODO cleanup tests
	@Test
	def void testArgs1(){
		val args=new CsvArgs(#["src/test/resources/test.csv"])
		Assert.assertEquals(0, args.blzIndex)
		Assert.assertEquals(1, args.kontoIndex)
		Assert.assertEquals(new File("src/test/resources/test.csv_converted").absoluteFile, args.fileTo)
		Assert.assertEquals(Charsets.ISO_8859_1, args.encoding)
		Assert.assertEquals(";", args.separator)
	}

	@Test(expected=typeof(FileNotFoundException))
	def void testArgs2(){
		new CsvArgs(#["src/test/resources/test2.csv"])
	}

	@Test(expected=typeof(IllegalArgumentException))
	def void testInputEqOutput(){
		new CsvArgs(#["src/test/resources/test.csv","-o","src/test/resources/test.csv"])
	}

	@Test
	def void testInput(){
		val args=new CsvArgs(#["-i","src/test/resources/test.csv"])
		Assert.assertEquals(new File("src/test/resources/test.csv").absoluteFile, args.file)
	}

	@Test
	def void testArgs4(){
		val args=new CsvArgs(#["-i","src/test/resources/test.csv","-o","blubbs"])
		Assert.assertEquals(new File("blubbs").absoluteFile, args.fileTo)
	}

	@Test
	def void testArgs5(){
		val args=new CsvArgs(#["--input","src/test/resources/test.csv","--output","blubbs"])
		Assert.assertEquals(new File("blubbs").absoluteFile, args.fileTo)
	}

	@Test(expected=typeof(IllegalArgumentException))
	def void testArgs6(){
		new CsvArgs(#["src/test/resources/test.csv","x","y"])
	}

	@Test
	def void testArgs7(){
		val args=new CsvArgs(#["src/test/resources/test.csv","-e","UTF-8"])
		Assert.assertEquals(Charsets.UTF_8, args.encoding)
	}

	@Test(expected=typeof(UnsupportedCharsetException))
	def void testArgs8(){
		new CsvArgs(#["src/test/resources/test.csv","--encoding","UTF-9"])
	}

	@Test
	def void testArgs9(){
		//index starting with 1
		var args=new CsvArgs(#["src/test/resources/test.csv","-b","4"])
		//index starting with 0
		Assert.assertEquals(2, args.kontoIndex)
		args=new CsvArgs(#["src/test/resources/test.csv","--bankcode","4"])
		Assert.assertEquals(2, args.kontoIndex)
	}

	@Test
	def void testArgs10(){
		//index starting with 1
		var args=new CsvArgs(#["src/test/resources/test.csv","-a","3"])
		//index starting with 0
		Assert.assertEquals(3, args.blzIndex)
		args=new CsvArgs(#["src/test/resources/test.csv","--account","3"])
		Assert.assertEquals(3, args.blzIndex)
	}

	@Test
	def void testArgs11(){
		var args=new CsvArgs(#["src/test/resources/test.csv","-s",","])
		Assert.assertEquals(",", args.separator)
		args=new CsvArgs(#["src/test/resources/test.csv","--separator",","])
		Assert.assertEquals(",", args.separator)
	}

	@Test
	@Ignore
	def void invokeConverter(){
		SimpleCsvConverter::main(newArrayList("src/test/resources/test.csv"))
	}

	@Test
	@Ignore
	def void convertSingle(){
		val res=new SimpleCsvConverter().getResult(new LegacyAccount(12345678,123L))
		println(res.iban+"    "+res.bic)
	}
}