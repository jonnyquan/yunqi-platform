package com.yunqi.rest.client;

public interface TokenProvider {
	
	public abstract String getAccessToken();
	public abstract String sign();

}
