package com.yunqi.apis.org.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;

import com.yunqi.apis.org.asyn.AsynMethodListener;

@Configuration
public class AsynApiConfig {
	
	@Autowired
	private JdkSerializationRedisSerializer redisSerializer;
	
	@Autowired
	private StringRedisTemplate redisTemplate;
//	
//	private Object createApi(Class<?> clazz){
//		AsyncallProxyFactory factory = new AsyncallProxyFactory(queueProvider());
//		return factory.create(clazz);
//	}
//
//	@Bean
//	public QueueProvider queueProvider() {
//		return new RedisTemplateQueueProvider(redisTemplate, redisSerializer);
//	}
	
	//不可以多次创建
	@Bean
	public AsynMethodListener asynMethodListener() {
		return new AsynMethodListener(redisTemplate, redisSerializer);
	}
//	
//	@Bean
//	public UserTestAsynApi userTestAsynApi() {
//		return (UserTestAsynApi) createApi(UserTestAsynApi.class);
//	}
	
}
