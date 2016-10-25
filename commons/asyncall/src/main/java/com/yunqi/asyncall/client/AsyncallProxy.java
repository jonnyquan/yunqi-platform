package com.yunqi.asyncall.client;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class AsyncallProxy implements InvocationHandler, Serializable{

	private static final long serialVersionUID = 5607059789355942804L;

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		return this.invoke(proxy, method, args);
	}

}
