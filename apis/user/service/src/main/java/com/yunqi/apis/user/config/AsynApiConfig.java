package com.yunqi.apis.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.yunqi.asyn.api.org.UserTestAsynApi;
import com.yunqi.asyncall.client.AsyncallProxyFactory;

@Configuration
public class AsynApiConfig {
	
	private Object createApi(Class<?> clazz){
		AsyncallProxyFactory factory = new AsyncallProxyFactory();
		return factory.create(clazz);
	}

	@Bean(name = "userTestAsynApi")
	public UserTestAsynApi userTestAsynApi() {
		return (UserTestAsynApi) createApi(UserTestAsynApi.class);
	}
	
}
