package com.yunqi.rest.service;

import java.io.Serializable;

public class AuthEntity implements Serializable{
	
	private static final long serialVersionUID = 8028953795904534020L;
	private String userId;
	private String accountId;
	private String account;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}

}
