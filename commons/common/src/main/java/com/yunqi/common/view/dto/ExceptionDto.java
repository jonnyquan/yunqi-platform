package com.yunqi.common.view.dto;

import java.io.Serializable;

public class ExceptionDto implements Serializable{

	private static final long serialVersionUID = -5566193307533411706L;
	
	private Integer code;
	private String msg;
	private Object data;
	
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
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
