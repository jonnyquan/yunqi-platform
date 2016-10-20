package com.yunqi.apis.user.api.dto;

import java.io.Serializable;

public class TokenDto implements Serializable{

	private static final long serialVersionUID = -8674494710142562010L;

	private String accessToken;
	
	private Long expireIn;

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public Long getExpireIn() {
		return expireIn;
	}

	public void setExpireIn(Long expireIn) {
		this.expireIn = expireIn;
	}
	
}
