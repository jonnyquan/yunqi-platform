package com.yunqi.apis.org.config;

import com.earven.asyncall.AsyncallConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import com.earven.asyncall.service.Module;

@Configuration
public class AsynApiConfig extends AsyncallConfig implements ApplicationContextAware {

	private static ApplicationContext applicationContext;

	public void setApplicationContext(ApplicationContext applicationContext) {
		AsynApiConfig.applicationContext = applicationContext;
	}

	@Override
	public ApplicationContext getApplicationContext() {
		return AsynApiConfig.applicationContext;
	}

	@Override
	public Module getModule() {
		return Module.ORG;
	}
	
}
