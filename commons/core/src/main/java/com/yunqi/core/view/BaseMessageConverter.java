package com.yunqi.core.view;

import java.io.IOException;
import java.lang.reflect.Type;

import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yunqi.common.view.dto.ExceptionDto;
import com.yunqi.common.view.dto.ResponseDto;
import com.yunqi.common.view.dto.ResponseState;

public class BaseMessageConverter extends MappingJackson2HttpMessageConverter{

	public BaseMessageConverter() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		this.setObjectMapper(objectMapper);
	}
	
	@Override
	protected void writeInternal(Object object, Type type, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
		
		if(object==null){
			super.writeInternal(object, type, outputMessage);
		}
		
		ResponseDto rd = new ResponseDto();
		if(object instanceof ExceptionDto){
			rd.setState(ResponseState.ERROR);
		}else{
			rd.setState(ResponseState.SUCCESS);
		}
		rd.setResult(object);
		
		super.writeInternal(rd, type, outputMessage);
		
	}

}
