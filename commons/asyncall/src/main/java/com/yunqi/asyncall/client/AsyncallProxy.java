package com.yunqi.asyncall.client;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.yunqi.asyncall.MethodMessage;

public class AsyncallProxy implements InvocationHandler, Serializable{

	private static final long serialVersionUID = 5607059789355942804L;
	
	public static final String METHOD_BROKER = "asyncall:method:broker";
	
	private static final String RETURN_VALUE_BROKER_PREFIX = "asyncall:return:broker:";
	
	@Autowired
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
		MethodMessage mm = new MethodMessage(returnValueBroker, method.getDeclaringClass().getName(), method.getName(), args);
		this.asynInvoke(METHOD_BROKER, mm);
		Object value = this.listenReturn(returnValueBroker);
		System.out.println(value);
		return value;
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
	private Object listenReturn(String returnValueBroker) {
		return queueProvider.pop(returnValueBroker);
	}

}
