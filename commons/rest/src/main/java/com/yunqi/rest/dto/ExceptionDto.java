package com.yunqi.rest.dto;

import java.io.Serializable;

public class ExceptionDto implements Serializable{

	private static final long serialVersionUID = -5566193307533411706L;
	
	private String code;
	private String msg;
	private Object data;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
}
