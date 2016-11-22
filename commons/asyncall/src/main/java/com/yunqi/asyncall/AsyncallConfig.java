package com.yunqi.asyncall;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;

import com.yunqi.asyncall.client.AsyncallProxyFactory;
import com.yunqi.asyncall.client.QueueProvider;
import com.yunqi.asyncall.client.RedisTemplateQueueProvider;
import com.yunqi.asyncall.service.AsynMethodListener;
import com.yunqi.common.asyn.Module;

/**
 * spring boot项目只需要继承次类，就可以获取异步接口调用的支持，
 * 客户端和服务端都继承这个配置
 */
public abstract class AsyncallConfig implements ApplicationContextAware {
	
	public final static String BROKER_KEY = "asyncall:method:broker";

	//提供对象的序列号工具
	@Autowired
	private JdkSerializationRedisSerializer redisSerializer;

	//提供缓存的存储
	@Autowired
	private StringRedisTemplate redisTemplate;

	//asyncall还原接口调用时需要的bean查找功能
	private static ApplicationContext applicationContext;
	
	//////////////////////////////////调用发送端使用（客户端）////////////////////////////////////

	public void setApplicationContext(ApplicationContext applicationContext) {
		AsyncallConfig.applicationContext = applicationContext;
	}

	//创建代理类
	public Object createApi(Class<?> clazz) {
		AsyncallProxyFactory factory = new AsyncallProxyFactory(queueProvider());
		return factory.create(clazz);
	}

	//异步接口调用的队列提供器
	@Bean
	public QueueProvider queueProvider() {
		return new RedisTemplateQueueProvider(redisTemplate, redisSerializer);
	}

	
	//////////////////////////////////调用相应端使用（服务端）////////////////////////////////////
	
	//监听异步接口调用，并处理
	// 不可以多次创建
	@Bean
	public AsynMethodListener asynMethodListener() {
		return new AsynMethodListener(applicationContext, redisTemplate, redisSerializer, getModule());
	}
	
	public abstract Module getModule();

}
