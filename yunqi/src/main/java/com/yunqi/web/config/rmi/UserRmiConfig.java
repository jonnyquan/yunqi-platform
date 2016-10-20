package com.yunqi.web.config.rmi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.yunqi.apis.user.api.AccountApi;
import com.yunqi.apis.user.api.ResourceApi;
import com.yunqi.apis.user.api.RoleApi;
import com.yunqi.apis.user.api.UserTestApi;
import com.yunqi.rest.client.RestProxyFactory;
import com.yunqi.web.config.rmi.token.RestTokenProvider;

@Configuration
public class UserRmiConfig {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private RestTokenProvider tokenProvider;

	private Object createApi(Class<?> clazz){
		RestProxyFactory factory = new RestProxyFactory(restTemplate, tokenProvider);
		return factory.create(clazz);
	}
	
	@Bean(name = "accountApi")
	public AccountApi accountApi() {
		return (AccountApi) createApi(AccountApi.class);
	}
	
	@Bean(name = "roleApi")
	public RoleApi roleApi() {
		return (RoleApi) createApi(RoleApi.class);
	}
	
	@Bean(name = "resourceApi")
	public ResourceApi resourceApi() {
		return (ResourceApi) createApi(ResourceApi.class);
	}
	
	@Bean(name = "userTestApi")
	public UserTestApi userTestApi() {
		return (UserTestApi) createApi(UserTestApi.class);
	}

}
