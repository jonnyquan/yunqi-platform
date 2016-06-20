package com.yunqi.config;

import java.net.MalformedURLException;

import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.caucho.hessian.client.HessianProxyFactory;
import com.yunqi.apis.user.api.UserApi;

@Configuration
public class RmiConfig implements EnvironmentAware{
	
	private RelaxedPropertyResolver rpr;

	@Override
	public void setEnvironment(Environment env) {
		this.rpr = new RelaxedPropertyResolver(env, "remoting.");
	}

	@Bean(name = "userApi")
	public UserApi userApi() {
		UserApi api = null;
		HessianProxyFactory factory = new HessianProxyFactory();
		try {
			api = (UserApi) factory.create(UserApi.class, rpr.getProperty("user.url"));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return api;
	}

}
