package com.yunqi.apis.org.config;

import org.springframework.context.annotation.Configuration;

import com.earven.rest.SimpleMvcConfig;

@Configuration
public class MvcConfig extends SimpleMvcConfig {
	
	@Override
	public String[] getAuthorizePath() {
		return new String[]{"/**"};
	}

	@Override
	public String[] getAuthorizeIgnoringPath() {
		return new String[]{};
	}

}