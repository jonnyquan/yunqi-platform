package com.yunqi.asyncall;

import java.io.Serializable;

public class ReturnMessage implements Serializable{
	
	private static final long serialVersionUID = -3918600776185605564L;
	
	private ReturnValueType type;
	private Object value;
	private Throwable exception;
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(type).append(":").append(value).append(":").append(exception);
		return sb.toString();
	}
	
	public ReturnValueType getType() {
		return type;
	}
	public void setType(ReturnValueType type) {
		this.type = type;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	public Throwable getException() {
		return exception;
	}
	public void setException(Throwable exception) {
		this.exception = exception;
	}

}
