package com.yunqi.rest.client;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import org.springframework.web.client.RestTemplate;

public class RestProxyFactory {

	private final ClassLoader _loader;
	private final RestTemplate _restTemplate;

	public RestProxyFactory(RestTemplate restTemplate) {
		this(Thread.currentThread().getContextClassLoader(), restTemplate);
	}

	public RestProxyFactory(ClassLoader loader, RestTemplate restTemplate) {
		_loader = loader;
		_restTemplate = restTemplate;
	}

	public Object create(Class<?> api){
		return create(api, _loader);
	}

	public Object create(Class<?> api, ClassLoader loader) {
		if (api == null) throw new NullPointerException("api must not be null for HessianProxyFactory.create()");
		InvocationHandler handler = null;

		// handler = new HessianProxy(url, this, api);
		// handler = new HttpProxy(url, this, api);
		handler = new RestProxy(_restTemplate);

		return Proxy.newProxyInstance(loader, new Class[] { api }, handler);
	}

}
