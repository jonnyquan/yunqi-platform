package com.yunqi.rest.util;

import java.util.UUID;

public class TokenUtil {
	
	public final static String ACCOUNT_TO_TOKEN_KEY = "yunqi:account:to:token:";
	public final static String TOKEY_KEY = "yunqi:token:";
	public final static int TOKEY_EXPIRE_TIME = 2*60*60;

	public static String getToke(){
		String token = null;
		UUID uuid = UUID.randomUUID();
		token = uuid.toString();
		token = token.replace("-", "");
		return token;
	}
	
}
