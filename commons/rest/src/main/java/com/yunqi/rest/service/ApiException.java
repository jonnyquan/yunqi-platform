package com.yunqi.rest.service;

public class ApiException extends RestException{

	private static final long serialVersionUID = 4040745672933628876L;
	
	public ApiException(Integer code, String msg) {
		super(code, msg);
	}

}
