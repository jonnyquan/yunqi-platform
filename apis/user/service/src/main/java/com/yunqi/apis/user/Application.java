package com.yunqi.apis.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"com.yunqi.apis.user", "com.yunqi.rest.service.scan"})
public class Application{
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}

}
