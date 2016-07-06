package com.yunqi.apis.user.api.dto;

import java.io.Serializable;

public class AccountDto implements Serializable{

	private static final long serialVersionUID = 5247382650520281531L;
	
	private String accountId;
	
	private String password;
	
	private UserDto user;

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

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}
	
}
