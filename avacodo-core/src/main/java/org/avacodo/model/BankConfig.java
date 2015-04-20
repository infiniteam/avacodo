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
package org.avacodo.model;

import com.google.common.base.*;

/**
 * use BankConfig.builder() to create a new configuration
 * */
public class BankConfig {
	private String checkMethod;
	private int ibanRule=-1;
	private int ibanVersion=-1;
	private boolean deletionAnnounced;
	private Integer succeedingBlz;
	private String bic;

	private BankConfig(){}

	public static Builder builder(){
		return new Builder();
	}

	public String getAccountCheckMethod() {
		return checkMethod;
	}

	public int getIbanRule() {
		return ibanRule;
	}

	public int getIbanRuleVersion() {
		return ibanVersion;
	}

	/**
	 * null if there is no succeeding bank code
	 * */
	public Integer getSucceedingBlz() {
		return succeedingBlz;
	}

	public String getBic() {
		return bic;
	}

	public boolean isDeletionAnnounced() {
		return deletionAnnounced;
	}

	public static class Builder{
		private BankConfig config=new BankConfig();

		/**
		 * account check method such as "00", "76", "A6"
		 * */
		public Builder checkMethod(String checkMethod) {
			config.checkMethod=checkMethod;
			return this;
		}

		/**
		 * iban rule including version, e.g. "004200" for rule 42 version 0
		 * or "003105" for rule 31 version 5
		 * */
		public Builder ibanRule(String ibanRule) {
			return ibanRule(Integer.parseInt(ibanRule));
		}

		/**
		 * iban rule including version, e.b. 4200 for rule 42 version 0
		 * or 3105 for rule 31 version 5
		 * */
		public Builder ibanRule(int ibanRule) {
			Preconditions.checkArgument(ibanRule>=0,"iban rule must be non-negative");
			config.ibanRule=ibanRule/100;
			config.ibanVersion=ibanRule%100;
			return this;
		}

		public Builder deletionAnnounced(boolean deletionAnnounced) {
			config.deletionAnnounced=deletionAnnounced;
			return this;
		}

		public Builder bic(String bic) {
			config.bic=bic;
			return this;
		}

		public Builder succeedingBlz(String blz){
			if(blz==null){
				config.succeedingBlz=null;
				return this;
			}else{
				return succeedingBlz(Integer.parseInt(blz));
			}
		}

		public Builder succeedingBlz(Integer blz){
			if(blz!=null && blz!=0){
				config.succeedingBlz=blz;
			}else{
				config.succeedingBlz=null;
			}
			return this;
		}

		public BankConfig build(){
			Preconditions.checkState(config.ibanRule>=0, "iban rule must be defined");
			Preconditions.checkNotNull(config.checkMethod, "account check method must be defined");
			if(config.succeedingBlz==null){
				Preconditions.checkNotNull(config.bic, "bic must be defined");
			}
			return config;
		}
	}
}
