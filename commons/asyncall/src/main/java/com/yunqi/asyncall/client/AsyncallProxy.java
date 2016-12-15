package com.yunqi.asyncall.client;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yunqi.asyncall.AsyncallConfig;
import com.yunqi.asyncall.MethodMessage;
import com.yunqi.asyncall.ReturnMessage;
import com.yunqi.asyncall.ReturnValueType;
import com.yunqi.asyncall.service.AsynApi;

public class AsyncallProxy implements InvocationHandler, Serializable{
	
	private final Logger logger = LoggerFactory.getLogger(AsyncallProxy.class);

	private static final long serialVersionUID = 5607059789355942804L;
	
	//返回值broker的前缀
	private static final String RETURN_VALUE_BROKER_PREFIX = "asyncall:return:broker:";
	
	//监听返回值得超时时间默认值
	private static final int RETURN_TIMEOUT = 10;
	
	//队列提供器
	private QueueProvider queueProvider;
	
	public AsyncallProxy(QueueProvider queueProvider){
		this.queueProvider = queueProvider;
	}
	
	private static String getReturnValueBroker(){
		return RETURN_VALUE_BROKER_PREFIX + UUID.randomUUID().toString().replace("-", "");
	}

	/**
	 * 接口的调用分为两步，
	 * 第一步，AsyncallProxy将接口调用信息push到methodBroker上
	 * 第二步，AsyncallProxy监听协商好的returnValueBroker（阻塞式pop），等待接口返回结果
	 */
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		String returnValueBroker = getReturnValueBroker();
		
		AsynApi asyApi = method.getDeclaringClass().getAnnotationsByType(AsynApi.class)[0];
		String methodBroker = asyApi.module().name().toLowerCase() + ":" + AsyncallConfig.BROKER_KEY;
		
		MethodMessage mm = new MethodMessage(returnValueBroker, method.getDeclaringClass(), method.getDeclaringClass().getSimpleName(), method.getName(), method.getParameterTypes(), args);
		this.asynInvoke(methodBroker, mm);
		ReturnMessage rm = this.listenReturn(returnValueBroker, RETURN_TIMEOUT);
		logger.info("Asyncall call[{}.{}], return[{}], exception[{}]", method.getDeclaringClass().getName(), method.getName(), rm.getValue(), rm.getException());
		if(rm.getType().equals(ReturnValueType.SUCESS)){
			return rm.getValue();
		}else if(rm.getType().equals(ReturnValueType.EXCEPTION)){
			throw rm.getException();
		}
		
		return null;
	}

	/**
	 * 异步调用接口，将调用信息发布到methodBroker队列
	 * @param methodBroker
	 * @param mm
	 */
	private void asynInvoke(String methodBroker, MethodMessage mm) {
		queueProvider.push(methodBroker, mm);
	}
	
	/**
	 * 监听接口调用的结果返回队列
	 */
	private ReturnMessage listenReturn(String returnValueBroker, int timeout) {
		return queueProvider.pop(returnValueBroker, timeout);
	}

}
