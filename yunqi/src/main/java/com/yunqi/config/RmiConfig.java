package com.yunqi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.yunqi.apis.user.api.UserApi;
import com.yunqi.core.view.HttpServiceExporter;

@Configuration
public class RmiConfig {

    @Bean(name="userApi")
    public UserApi userApi(){  
    	HttpServiceExporter exporter = new HttpServiceExporter();  
        exporter.setServiceInterface(UserApi.class);  
        exporter.setServiceUrl("http://127.0.0.1:8080/user"); 
        UserApi userApi = (UserApi) exporter.getService();
        return userApi;
    }  

}
