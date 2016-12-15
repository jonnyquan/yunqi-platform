package com.yunqi.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

import com.earven.rest.client.RestProxyFactory;
import com.yunqi.apis.user.api.TokenApi;
import com.yunqi.web.config.rmi.token.RestTokenProvider;

@Configuration
public class BeanConfig implements EnvironmentAware{
	
	private RelaxedPropertyResolver rpr;
	
	@Autowired
	private RestTemplateBuilder restTemplateBuilder;
	
	@Override
	public void setEnvironment(Environment env) {
		this.rpr = new RelaxedPropertyResolver(env);
	}
	
	@Bean(name = "userRestTemplate")
	public RestTemplate restTemplate(){
		return restTemplateBuilder.rootUri(rpr.getProperty("remoting.user.url")).build();
	}
	
	@Bean(name="tokenApi")
	public TokenApi tokenApi() throws Exception {
		RestProxyFactory factory = new RestProxyFactory(restTemplate());
		factory.create(TokenApi.class);
		return (TokenApi) factory.create(TokenApi.class);
	}
		
	@Bean(name="tokenProvider")
	public RestTokenProvider tokenProvider() throws Exception {
		RestTokenProvider tokenProvider = new RestTokenProvider();
		tokenProvider.setTokenApi(tokenApi());
		tokenProvider.setAccountId(rpr.getProperty("remoting.account-id"));
		tokenProvider.setPassword(rpr.getProperty("remoting.password"));
		tokenProvider.sign();
		return tokenProvider;
	}
	
}


