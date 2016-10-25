package com.yunqi.asyncall.client;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class AsyncallProxyFactory {

	private final ClassLoader _loader;
	
	public AsyncallProxyFactory() {
		this(Thread.currentThread().getContextClassLoader());
	}


	public AsyncallProxyFactory(ClassLoader loader) {
		_loader = loader;
	}

	public Object create(Class<?> api){
		return create(api, _loader);
	}

	public Object create(Class<?> api, ClassLoader loader) {
		if (api == null) throw new NullPointerException("api must not be null for HessianProxyFactory.create()");
		InvocationHandler handler = null;
		handler = new AsyncallProxy();
		return Proxy.newProxyInstance(loader, new Class[] { api }, handler);
	}

}
