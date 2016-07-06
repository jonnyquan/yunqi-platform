package com.yunqi.apis.user.domain;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.yunqi.core.entity.MongoEntity;

/**
 * 账号.
 */
@Document
public class Account extends MongoEntity<Account>{

	private static final long serialVersionUID = 4792321611721758123L;

	private String accountId;
	
	private String password;
	
	@DBRef
	private User user;
	
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
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
}
