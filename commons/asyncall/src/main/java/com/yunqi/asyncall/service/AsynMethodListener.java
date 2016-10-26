package com.yunqi.asyncall.service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;

import com.yunqi.asyncall.MethodMessage;
import com.yunqi.asyncall.ReturnMessage;
import com.yunqi.asyncall.ReturnValueType;

public class AsynMethodListener {

	public AsynMethodListener(ApplicationContext applicationContext, StringRedisTemplate redisTemplate, JdkSerializationRedisSerializer redisSerializer){
		new Thread(new AsynMethodProcess(applicationContext, redisTemplate, redisSerializer)).start();
	}
	
}

/**
 * 异步接口处理器
 * 阻塞式监听methodBroker队列（接口调用队列），
 * 当收到接口调用后，多线程处理，
 * 处理完后把结果发布到MethodMessage定义的returnValueKey队列上（请求者会在此监听）
 */
class AsynMethodProcess implements Runnable{
	
	public final Logger logger = LoggerFactory.getLogger(AsynMethodProcess.class);
	
	public static final String METHOD_BROKER = "asyncall:method:broker";
	
	private static final long EXPIRE_TIME = 3600;
	
	private ApplicationContext applicationContext;
	
	private StringRedisTemplate redisTemplate;
	
	private JdkSerializationRedisSerializer redisSerializer;
	
	public AsynMethodProcess(ApplicationContext applicationContext, StringRedisTemplate redisTemplate, JdkSerializationRedisSerializer redisSerializer){
		this.applicationContext = applicationContext;
		this.redisTemplate = redisTemplate;
		this.redisSerializer = redisSerializer;
	}
	
	@Override
	public void run() {
		redisTemplate.execute(new RedisCallback<Boolean>() {
			@Override
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				while(true){
					List<byte[]> rsbs = connection.bLPop(0, METHOD_BROKER.getBytes());
					MethodMessage mm = (MethodMessage) redisSerializer.deserialize(rsbs.get(1));
					byte[] returnValueKeyByte = mm.getReturnValueKey().getBytes();
					ReturnMessage rm = process(mm);
					connection.lPush(returnValueKeyByte, redisSerializer.serialize(rm));
					connection.expire(returnValueKeyByte, EXPIRE_TIME);
					logger.debug("Asyncall receive[{}.{}], return[{}]", mm.getClazz().getName(), mm.getMethodName(), rm.toString());
				}
			}
			
		});
	}
	
	public ReturnMessage process(MethodMessage mm){
		
		ReturnMessage rm = new ReturnMessage();
		
		Object bean = applicationContext.getBean(mm.getClazz());
		Object returnValue = null;
        Class<?> beanClass = bean.getClass(); 
		try {
	        Method method = beanClass.getDeclaredMethod(mm.getMethodName(), mm.getParameterTypes()); 
	        returnValue = method.invoke(bean, mm.getArgs());
	        rm.setType(ReturnValueType.SUCESS);
	        rm.setValue(returnValue);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			rm.setType(ReturnValueType.EXCEPTION);
			rm.setException(e.getTargetException());
		} 
		return rm;
	}
	
}
