package com.yunqi.asyncall.client;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class AsyncallProxyFactory {

	private final ClassLoader _loader;
	
	private final QueueProvider _queueProvider;
	
	public AsyncallProxyFactory(QueueProvider queueProvider) {
		this(Thread.currentThread().getContextClassLoader(), queueProvider);
	}
	
	public AsyncallProxyFactory(ClassLoader loader, QueueProvider queueProvider) {
		_loader = loader;
		_queueProvider = queueProvider;
	}

	public Object create(Class<?> api){
		return create(api, _loader);
	}

	public Object create(Class<?> api, ClassLoader loader) {
		if (api == null) throw new NullPointerException("api must not be null for HessianProxyFactory.create()");
		InvocationHandler handler = null;
		handler = new AsyncallProxy(_queueProvider);
		return Proxy.newProxyInstance(loader, new Class[] { api }, handler);
	}

}
