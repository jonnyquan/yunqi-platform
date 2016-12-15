package com.yunqi.web.config.rmi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.earven.rest.client.RestProxyFactory;
import com.earven.rest.client.TokenProvider;
import com.yunqi.apis.org.api.OrgTestApi;

@Configuration
public class OrgRmiConfig {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private TokenProvider tokenProvider;

	private Object createApi(Class<?> clazz){
		RestProxyFactory factory = new RestProxyFactory(restTemplate, tokenProvider);
		return factory.create(clazz);
	}

	@Bean(name = "orgTestApi")
	public OrgTestApi accountApi() {
		return (OrgTestApi) createApi(OrgTestApi.class);
	}

}
