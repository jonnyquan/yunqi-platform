package com.yunqi.web.config.rmi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

import com.yunqi.apis.org.api.OrgTestApi;
import com.yunqi.rest.client.RestProxyFactory;

@Configuration
public class OrgRmiConfig implements EnvironmentAware{
	
	private RelaxedPropertyResolver rpr;

	@Override
	public void setEnvironment(Environment env) {
		this.rpr = new RelaxedPropertyResolver(env, "remoting.");
	}
	
	@Autowired
	private RestTemplateBuilder restTemplateBuilder;

	@Bean(name = "userRestTemplate")
	public RestTemplate restTemplate(){
		return restTemplateBuilder.rootUri(rpr.getProperty("user.url")).build();
	}
	
	private Object createApi(Class<?> clazz){
		RestProxyFactory factory = new RestProxyFactory(restTemplate());
		return factory.create(clazz);
	}
	
//	private Object createApi(Class<?> clazz){
//		Object api = null;
//		HessianProxyFactory factory = new HessianProxyFactory();
//		try {
//			api = factory.create(clazz, rpr.getProperty("org.url"));
//		} catch (MalformedURLException e) {
//			e.printStackTrace();
//		}
//		return api;
//	}

	@Bean(name = "orgTestApi")
	public OrgTestApi accountApi() {
		return (OrgTestApi) createApi(OrgTestApi.class);
	}

}
