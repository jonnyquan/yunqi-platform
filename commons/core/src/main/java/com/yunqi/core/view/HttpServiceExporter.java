package com.yunqi.core.view;

import java.lang.reflect.Proxy;

public class HttpServiceExporter {

	private Class<?> serviceInterface;
	private String serviceUrl;
	private Object service;
	
	public Class<?> getServiceInterface() {
		return serviceInterface;
	}
	public void setServiceInterface(Class<?> serviceInterface) {
		this.serviceInterface = serviceInterface;
	}
	public String getServiceUrl() {
		return serviceUrl;
	}
	public void setServiceUrl(String serviceUrl) {
		this.serviceUrl = serviceUrl;
	}
	
	public Object getService() {
		if(service==null){
	        RpcMultiProxyServer proxy = new RpcMultiProxyServer(service);  
	        service = Proxy.newProxyInstance(service.getClass().getClassLoader(), service.getClass().getInterfaces(), proxy); 
		}
		return service;
	}
	
}
