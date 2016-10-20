package com.yunqi.rest.client;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import org.springframework.web.client.RestTemplate;

public class RestProxyFactory {

	private final ClassLoader _loader;
	private final RestTemplate _restTemplate;
	private final TokenProvider _tokenProvider;
	
	public RestProxyFactory(RestTemplate restTemplate) {
		this(Thread.currentThread().getContextClassLoader(), restTemplate, null);
	}

	public RestProxyFactory(RestTemplate restTemplate, TokenProvider tokenProvider) {
		this(Thread.currentThread().getContextClassLoader(), restTemplate, tokenProvider);
	}

	public RestProxyFactory(ClassLoader loader, RestTemplate restTemplate, TokenProvider tokenProvider) {
		_loader = loader;
		_restTemplate = restTemplate;
		_tokenProvider = tokenProvider;
	}

	public Object create(Class<?> api){
		return create(api, _loader);
	}

	public Object create(Class<?> api, ClassLoader loader) {
		if (api == null) throw new NullPointerException("api must not be null for HessianProxyFactory.create()");
		InvocationHandler handler = null;
		handler = new RestProxy(_restTemplate, _tokenProvider);
		return Proxy.newProxyInstance(loader, new Class[] { api }, handler);
	}

}
