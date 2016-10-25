package com.yunqi.asyn.api.org.dto;

import java.io.Serializable;

public class AccountDto implements Serializable{

	private static final long serialVersionUID = 5247382650520281531L;
	
	private String accountId;
	
	private String password;
	
	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
