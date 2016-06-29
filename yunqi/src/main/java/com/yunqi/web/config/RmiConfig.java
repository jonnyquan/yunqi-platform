package com.yunqi.web.config;

import java.net.MalformedURLException;

import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.caucho.hessian.client.HessianProxyFactory;
import com.yunqi.apis.user.api.AccountApi;
import com.yunqi.apis.user.api.ResourceApi;
import com.yunqi.apis.user.api.RoleApi;

@Configuration
public class RmiConfig implements EnvironmentAware{
	
	private RelaxedPropertyResolver rpr;

	@Override
	public void setEnvironment(Environment env) {
		this.rpr = new RelaxedPropertyResolver(env, "remoting.");
	}
	
	private Object createApi(Class<?> clazz){
		Object api = null;
		HessianProxyFactory factory = new HessianProxyFactory();
		try {
			api = factory.create(clazz, rpr.getProperty("user.url"));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return api;
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

}
