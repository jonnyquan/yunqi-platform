package com.yunqi.rest.service;

public class RestException extends Exception{

	private static final long serialVersionUID = -825184279697358059L;
	
	private Integer code;

	public RestException(Integer code, String msg) {
		super(msg);
		this.code = code;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

}
