package com.yunqi.apis.org.config;

import com.earven.asyncall.AsyncallConfig;
import com.earven.asyncall.client.QueueProvider;
import com.earven.asyncall.client.RedisTemplateQueueProvider;
import com.yunqi.asyn.api.org.OrgTestAsynApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.earven.asyncall.service.Module;
import redis.clients.jedis.JedisPool;

@Configuration
public class AsynApiConfig extends AsyncallConfig implements ApplicationContextAware {

	private static ApplicationContext applicationContext;

	public void setApplicationContext(ApplicationContext applicationContext) {
		AsynApiConfig.applicationContext = applicationContext;
	}

	@Override
	public ApplicationContext getApplicationContext() {
		return AsynApiConfig.applicationContext;
	}

	@Override
	public Module getModule() {
		return Module.ORG;
	}
	
}
