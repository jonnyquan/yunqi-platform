package com.yunqi.apis.user.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;

import com.yunqi.apis.user.asyn.AsynMethodListener;
import com.yunqi.asyn.api.org.UserTestAsynApi;
import com.yunqi.asyncall.MethodMessage;
import com.yunqi.asyncall.client.AsyncallProxyFactory;
import com.yunqi.asyncall.client.QueueProvider;

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
	
	//不可以多次创建
	@Bean
	public AsynMethodListener asynMethodListener() {
		return new AsynMethodListener(redisTemplate, redisSerializer);
	}
	
	@Bean(name = "userTestAsynApi")
	public UserTestAsynApi userTestAsynApi() {
		return (UserTestAsynApi) createApi(UserTestAsynApi.class);
	}
	
}

/**
 * queue提供者，
 * 接口的调用分为两步，
 * 第一步，AsyncallProxy将接口调用信息push到methodBroker上
 * 第二步，AsyncallProxy监听协商好的returnValueBroker（阻塞式pop），等待接口返回结果
 */
class RedisTemplateQueueProvider implements QueueProvider{
	
	private StringRedisTemplate redisTemplate;
	
	private JdkSerializationRedisSerializer redisSerializer;
	
	public RedisTemplateQueueProvider(StringRedisTemplate redisTemplate, JdkSerializationRedisSerializer redisSerializer){
		this.redisTemplate = redisTemplate;
		this.redisSerializer = redisSerializer;
	}

	/**
	 * methodBroker:接口调用队列的key
	 */
	@Override
	public void push(String methodBroker, MethodMessage mm) {
		redisTemplate.execute(new RedisCallback<Boolean>() {
			@Override
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				connection.lPush(methodBroker.getBytes(), redisSerializer.serialize(mm));
				return true;
			}
		});
	}

	/**
	 * returnValueBroker：异步调用后，返回值监听的队列的key
	 */
	@Override
	public Object pop(String returnValueBroker) {
		return redisTemplate.execute(new RedisCallback<Object>() {
			@Override
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				List<byte[]> rsbs = connection.bLPop(100, returnValueBroker.getBytes());
				return redisSerializer.deserialize(rsbs.get(1));
			}
		});
	}
	
}
