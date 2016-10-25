package com.yunqi.asyncall;

import java.io.Serializable;

/**
 * 异步接口调用消息对象
 * 负责将接口数据还原到服务端，从而来完成数据处理
 */
public class MethodMessage implements Serializable{

	private static final long serialVersionUID = 3591859226434793089L;

	private String returnValueKey;
	
//	private Method method;
	
	private String clazz;
	
	private String name;
	
	private Object[] args;

	public MethodMessage(String returnValueKey, String clazz, String name, Object[] args){
		this.returnValueKey = returnValueKey;
		this.clazz = clazz;
		this.name = name;
		this.args = args;
	}

	public String getReturnValueKey() {
		return returnValueKey;
	}

	public void setReturnValueKey(String returnValueKey) {
		this.returnValueKey = returnValueKey;
	}

	public String getClazz() {
		return clazz;
	}

	public void setClazz(String clazz) {
		this.clazz = clazz;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Object[] getArgs() {
		return args;
	}

	public void setArgs(Object[] args) {
		this.args = args;
	}
	
}
