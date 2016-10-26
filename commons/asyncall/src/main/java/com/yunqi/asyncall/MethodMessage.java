package com.yunqi.asyncall;

import java.io.Serializable;

/**
 * 异步接口调用消息对象
 * 负责将接口数据还原到服务端，从而来完成数据处理
 */
public class MethodMessage implements Serializable{

	private static final long serialVersionUID = 3591859226434793089L;

	private String returnValueKey;
	
	private Class<?> clazz;
	
	private String beanName;
	
	private String methodName;
	
	private Class<?>[] parameterTypes;
			
	private Object[] args;

	public MethodMessage(String returnValueKey, Class<?> clazz, String beanName, String methodName, Class<?>[] parameterTypes, Object[] args){
		this.returnValueKey = returnValueKey;
		this.clazz = clazz;
		this.beanName = beanName;
		this.methodName = methodName;
		this.parameterTypes = parameterTypes;
		this.args = args;
	}

	public String getReturnValueKey() {
		return returnValueKey;
	}

	public void setReturnValueKey(String returnValueKey) {
		this.returnValueKey = returnValueKey;
	}

	public String getBeanName() {
		return beanName;
	}

	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public Class<?>[] getParameterTypes() {
		return parameterTypes;
	}

	public void setParameterTypes(Class<?>[] parameterTypes) {
		this.parameterTypes = parameterTypes;
	}

	public Object[] getArgs() {
		return args;
	}

	public void setArgs(Object[] args) {
		this.args = args;
	}

	public Class<?> getClazz() {
		return clazz;
	}

	public void setClazz(Class<?> clazz) {
		this.clazz = clazz;
	}

}
