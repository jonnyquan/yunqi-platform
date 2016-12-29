package com.yunqi.cloud.cluster;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@EnableDiscoveryClient
@EnableFeignClients(basePackages="com.yunqi.api")
@SpringBootApplication(scanBasePackages={"com.yunqi.cloud.cluster"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}