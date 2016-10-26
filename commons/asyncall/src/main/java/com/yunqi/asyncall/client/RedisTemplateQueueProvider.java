package com.yunqi.asyncall.client;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;

import com.yunqi.asyncall.MethodMessage;
import com.yunqi.asyncall.ReturnMessage;

/**
 * queue提供者，
 * 接口的调用分为两步，
 * 第一步，AsyncallProxy将接口调用信息push到methodBroker上
 * 第二步，AsyncallProxy监听协商好的returnValueBroker（阻塞式pop），等待接口返回结果
 */
public class RedisTemplateQueueProvider implements QueueProvider{
	
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
	public ReturnMessage pop(String returnValueBroker, int timeout) {
		return redisTemplate.execute(new RedisCallback<ReturnMessage>() {
			@Override
			public ReturnMessage doInRedis(RedisConnection connection) throws DataAccessException {
				List<byte[]> rsbs = connection.bLPop(timeout, returnValueBroker.getBytes());
				return (ReturnMessage) redisSerializer.deserialize(rsbs.get(1));
			}
		});
	}
	
}

