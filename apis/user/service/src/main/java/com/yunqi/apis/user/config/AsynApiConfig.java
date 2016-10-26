package com.yunqi.apis.user.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;

import com.yunqi.asyn.api.org.OrgTestAsynApi;
import com.yunqi.asyncall.client.AsyncallProxyFactory;
import com.yunqi.asyncall.client.QueueProvider;
import com.yunqi.asyncall.client.RedisTemplateQueueProvider;

@Configuration
public class AsynApiConfig {
	
	@Autowired
	private JdkSerializationRedisSerializer redisSerializer;
	
	@Autowired
	private StringRedisTemplate redisTemplate;
	
	private Object createApi(Class<?> clazz){
		AsyncallProxyFactory factory = new AsyncallProxyFactory(queueProvider());
		return factory.create(clazz);
	}

	@Bean
	public QueueProvider queueProvider() {
		return new RedisTemplateQueueProvider(redisTemplate, redisSerializer);
	}
	
	@Bean("orgTestAsynApi")
	public OrgTestAsynApi orgTestAsynApi() {
		return (OrgTestAsynApi) createApi(OrgTestAsynApi.class);
	}
	
}
