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
package org.avacodo.conversion.iban.rules;

import org.avacodo.model.*;

//rule enums have the form RXXXX_YY where XXXX is the iban rule number and YY is the version number
//by convention for each enum a corresponding Rule class (named RuleXXXXYY) must exist
enum Rules {

	R0000_00,
	R0001_00,
	R0002_00,
	R0003_00,
	R0004_00,
	R0005_00,
	R0005_01,
	R0005_02,
	R0005_03, // changes already implemented in Rule000502
	R0006_00,
	R0007_00,
	R0008_00,
	R0009_00,
	R0010_00,
	R0010_01, // changes already implemented in Rule001000
	R0011_00,
	R0012_00,
	R0012_01, // no changes, BIC can be read from bank code file
	R0013_00,
	R0013_01, // no changes, BIC can be read from bank code file
	R0014_00,
	R0015_00,
	R0015_01, // changes already implemented in Rule001500
	R0016_00,
	R0017_00,
	R0018_00,
	R0019_00,
	R0020_00,
	R0020_01, // changes that were communicated via email by the Bundesbank on August 30, 2013
	R0020_02, // changes already implemented in Rule002001
	R0021_00,
	R0021_01, // changes already implemented in Rule002100
	R0022_00,
	R0023_00,
	R0024_00,
	R0025_00,
	R0026_00,
	R0027_00,
	R0028_00,
	R0029_00,
	R0030_00,
	R0031_00,
	R0031_01, // no changes
	R0032_00,
	R0033_00,
	R0033_01, // no changes
	R0034_00,
	R0035_00,
	R0035_01, // no changes
	R0036_00,
	R0037_00,
	R0038_00,
	R0039_00,
	R0040_00,
	R0040_01, // BIC correction already implemented in Rule004000
	R0041_00,
	R0042_00,
	R0043_00,
	R0043_01, // changes already implemented in Rule004300
	R0044_00,
	R0045_00,
	R0045_01,
	R0046_00,
	R0047_00,
	R0048_00,
	R0049_00,
	R0050_00,
	R0051_00,
	R0052_00,
	R0053_00,
	R0054_00,
	R0054_01,
	R0055_00,
	R0056_00,
	R0057_00;

	final Rule rule;

	Rules() {
		if(!this.name().matches("R\\d{4}_\\d{2}")){
			throw new IllegalStateException("illegal rule enum "+this.name());
		}
		try {
			Class<?> clazz = this.getClass().getClassLoader().loadClass(getRuleName());
			rule=(Rule) clazz.getDeclaredConstructors()[0].newInstance();
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

	private String getRuleName(){
		StringBuilder b=new StringBuilder(this.getClass().getPackage().getName());
		b.append(".Rule");
		b.append(this.name().substring(1).replaceAll("_", ""));
		return b.toString();
	}

	static Rule from(BankConfig c){
		int r=c.getIbanRule();
		int v=c.getIbanRuleVersion();
		String ruleName=String.format("R%04d_%02d", r,v);
		try {
			return valueOf(ruleName).rule;
		} catch (Exception e) {
			return null;
		}
	}
}
