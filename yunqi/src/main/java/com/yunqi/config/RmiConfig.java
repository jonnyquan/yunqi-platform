package com.yunqi.config;

import java.net.MalformedURLException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.caucho.hessian.client.HessianProxyFactory;
import com.yunqi.apis.user.api.UserApi;

@Configuration
public class RmiConfig {

//    @Bean(name="userApi")
//    public UserApi userApi(){  
//    	HttpServiceExporter exporter = new HttpServiceExporter();  
//        exporter.setServiceInterface(UserApi.class);  
//        exporter.setServiceUrl("http://127.0.0.1:8080/user"); 
//        UserApi userApi = (UserApi) exporter.getService();
//        return userApi;
//    }  
	
	
	@Bean(name = "userApi")
	public UserApi userApi() {
		UserApi api = null;
		HessianProxyFactory factory = new HessianProxyFactory();
		try {
			api = (UserApi) factory.create(UserApi.class, "http://127.0.0.1:8080/user/user/getAccount");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return api;
	}

}
