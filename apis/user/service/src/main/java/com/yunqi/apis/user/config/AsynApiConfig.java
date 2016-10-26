package com.yunqi.apis.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.yunqi.asyn.api.org.OrgTestAsynApi;
import com.yunqi.asyncall.AsyncallConfig;

@Configuration
public class AsynApiConfig extends AsyncallConfig{
	
	@Bean("orgTestAsynApi")
	public OrgTestAsynApi orgTestAsynApi() {
		return (OrgTestAsynApi) createApi(OrgTestAsynApi.class);
	}
	
}
