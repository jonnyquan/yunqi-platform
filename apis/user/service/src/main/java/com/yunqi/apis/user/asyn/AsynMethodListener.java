package com.yunqi.apis.user.asyn;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;

import com.yunqi.asyncall.MethodMessage;

public class AsynMethodListener {

	public AsynMethodListener(StringRedisTemplate redisTemplate, JdkSerializationRedisSerializer redisSerializer){
		new Thread(new AsynMethodProcess(redisTemplate, redisSerializer)).start();
	}
	
}

/**
 * 异步接口处理器
 * 阻塞式监听methodBroker队列（接口调用队列），
 * 当收到接口调用后，多线程处理，
 * 处理完后把结果发布到MethodMessage定义的returnValueKey队列上（请求者会在此监听）
 */
class AsynMethodProcess implements Runnable{
	
	public static final String METHOD_BROKER = "asyncall:method:broker";
	
	private StringRedisTemplate redisTemplate;
	
	private JdkSerializationRedisSerializer redisSerializer;
	
	public AsynMethodProcess(StringRedisTemplate redisTemplate, JdkSerializationRedisSerializer redisSerializer){
		this.redisTemplate = redisTemplate;
		this.redisSerializer = redisSerializer;
	}
	
	@Override
	public void run() {
		redisTemplate.execute(new RedisCallback<Boolean>() {
			@Override
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				while(true){
					List<byte[]> rsbs = connection.bLPop(10000, METHOD_BROKER.getBytes());
					MethodMessage mm = (MethodMessage) redisSerializer.deserialize(rsbs.get(1));
					String returnValueKey = mm.getReturnValueKey();
					connection.lPush(returnValueKey.getBytes(), redisSerializer.serialize("xx"));
				}
			}
			
		});
	}
	
}
