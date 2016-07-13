package com.yunqi.rest.dto;

import java.io.Serializable;

public class ResponseDto implements Serializable{

	private static final long serialVersionUID = -3359675235870716178L;
	
	private ResponseState state;
	private Object result;
	
	public ResponseDto(){}
	
	public ResponseDto(ResponseState state, Object result){
		this.state = state;
		this.result = result;
	}
	
	public ResponseState getState() {
		return state;
	}
	public void setState(ResponseState state) {
		this.state = state;
	}
	public Object getResult() {
		return result;
	}
	public void setResult(Object result) {
		this.result = result;
	}
	
}
