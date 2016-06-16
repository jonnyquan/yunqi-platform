package com.yunqi.core.view;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class RpcMultiProxyServer implements InvocationHandler{

    private Object target;  
    
    public RpcMultiProxyServer() {  
        super();  
    }  
  
    public RpcMultiProxyServer(Object target) {  
        super();  
        this.target = target;  
    }

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		return method.invoke(target, args);
	}
	
}
