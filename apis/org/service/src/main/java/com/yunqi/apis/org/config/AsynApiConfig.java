package com.yunqi.apis.org.config;

import org.springframework.context.annotation.Configuration;

import com.yunqi.asyncall.AsyncallConfig;
import com.yunqi.common.asyn.Module;

@Configuration
public class AsynApiConfig extends AsyncallConfig{

	@Override
	public Module getModule() {
		return Module.ORG;
	}
	
}
