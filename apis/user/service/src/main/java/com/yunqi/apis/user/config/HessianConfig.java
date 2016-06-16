package com.yunqi.apis.user.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiServiceExporter;

import com.yunqi.apis.user.api.UserApi;
import com.yunqi.apis.user.controller.UserController;

@Configuration
public class HessianConfig {
	
	@Autowired
	private UserController userService;

//	@Bean(name = "accountRemoteService")
//	public HessianServiceExporter accountRemoteService() throws Exception {
//		HessianServiceExporter hse = new HessianServiceExporter();
//		hse.setService(userService);
//		hse.setServiceInterface(UserApi.class);
//		return hse;
//	}
	
    @Bean  
    public RmiServiceExporter initRmiServiceExporter(){  
        RmiServiceExporter exporter=new RmiServiceExporter();  
        exporter.setServiceInterface(UserApi.class);  
        exporter.setServiceName("rmiUserService");  
        exporter.setService(userService);  
        exporter.setServicePort(6666);  
        return exporter;  
    }  
	
}
