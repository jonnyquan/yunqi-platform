package com.yunqi.apis.user.config;

import org.springframework.context.annotation.Configuration;

import com.yunqi.rest.SimpleMvcConfig;


@Configuration
public class MvcConfig extends SimpleMvcConfig {

	@Override
	public String[] getAuthorizePath() {
		return new String[]{"/**"};
	}

	@Override
	public String[] getAuthorizeIgnoringPath() {
		return new String[]{"/token"};
	}

}