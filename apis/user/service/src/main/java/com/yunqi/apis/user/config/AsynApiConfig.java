package com.yunqi.apis.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.yunqi.asyn.api.org.OrgTestAsynApi;
import com.yunqi.asyncall.AsyncallConfig;
import com.yunqi.common.asyn.Module;

@Configuration
public class AsynApiConfig extends AsyncallConfig{
	
	@Bean("orgTestAsynApi")
	public OrgTestAsynApi orgTestAsynApi() {
		return (OrgTestAsynApi) createApi(OrgTestAsynApi.class);
	}

	@Override
	public Module getModule() {
		return Module.USER;
	}
	
}
