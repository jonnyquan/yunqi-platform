package com.yunqi.web.config.rmi;

import java.net.MalformedURLException;

import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.caucho.hessian.client.HessianProxyFactory;
import com.yunqi.apis.org.api.OrgTestApi;

@Configuration
public class OrgRmiConfig implements EnvironmentAware{
	
	private RelaxedPropertyResolver rpr;

	@Override
	public void setEnvironment(Environment env) {
		this.rpr = new RelaxedPropertyResolver(env, "remoting.");
	}
	
	private Object createApi(Class<?> clazz){
		Object api = null;
		HessianProxyFactory factory = new HessianProxyFactory();
		try {
			api = factory.create(clazz, rpr.getProperty("org.url"));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return api;
	}

	@Bean(name = "orgTestApi")
	public OrgTestApi accountApi() {
		return (OrgTestApi) createApi(OrgTestApi.class);
	}

}
