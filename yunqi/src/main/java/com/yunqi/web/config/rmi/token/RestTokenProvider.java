package com.yunqi.web.config.rmi.token;

import java.util.Date;

import com.earven.rest.client.TokenProvider;
import com.earven.rest.service.RestException;
import com.yunqi.apis.user.api.TokenApi;
import com.yunqi.apis.user.api.dto.TokenDto;

public class RestTokenProvider implements TokenProvider{
	
	private TokenApi tokenApi;
	private String accountId;
	private String password;
	
	private static String accessToken;
	private static Long expireIn;
	
	@Override
	public String sign(){
		accessToken = null;
		expireIn = null;
		String token = getAccessToken();
		return token;
	}
	
	@Override
	public String getAccessToken() {
		String token = getAccessTokenLocal();
		if(token==null){
			TokenDto tokenDto = null;
			try {
				tokenDto = tokenApi.token(accountId, password);
			} catch (RestException e) {
				e.printStackTrace();
			}
			accessToken = tokenDto.getAccessToken();
			expireIn = tokenDto.getExpireIn();
			token = getAccessTokenLocal();
		}
		return token;
	}
	
	private String getAccessTokenLocal(){
		Date now = new Date();
		if(expireIn==null || expireIn>now.getTime()){
			return null;
		}
		return accessToken;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setTokenApi(TokenApi tokenApi) {
		this.tokenApi = tokenApi;
	}

}
