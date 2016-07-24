package com.yunqi.rest.service;

public class RestException extends Exception{

	private static final long serialVersionUID = -825184279697358059L;
	
	private String code;

	public RestException(String code, String msg) {
		super(msg);
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
