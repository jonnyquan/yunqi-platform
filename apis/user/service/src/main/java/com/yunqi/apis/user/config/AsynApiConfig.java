package com.yunqi.apis.user.config;

import com.earven.asyncall.AsyncallConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;

import com.earven.asyncall.service.Module;
import com.yunqi.asyn.api.org.OrgTestAsynApi;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AsynApiConfig extends AsyncallConfig implements ApplicationContextAware{

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
        return Module.USER;
    }
	
	@Bean("orgTestAsynApi")
	public OrgTestAsynApi orgTestAsynApi() {
		return (OrgTestAsynApi) createApi(OrgTestAsynApi.class);
	}

}
