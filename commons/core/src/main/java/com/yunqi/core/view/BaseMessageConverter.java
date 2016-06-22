package com.yunqi.core.view;

import java.io.IOException;
import java.lang.reflect.Type;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonInputMessage;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
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

	@Override
	protected Object readInternal(Class<?> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
		return super.readInternal(clazz, inputMessage);
	}

	@Override
	public Object read(Type type, Class<?> contextClass, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
		JavaType javaType = getJavaType(type, contextClass);
		return readJavaType(javaType, inputMessage);
//		ResponseDto d = new ResponseDto();
//		d.setResult(1);
//		return d;
	}

	private Object readJavaType(JavaType javaType, HttpInputMessage inputMessage) {
		try {
			if (inputMessage instanceof MappingJacksonInputMessage) {
				Class<?> deserializationView = ((MappingJacksonInputMessage) inputMessage).getDeserializationView();
				if (deserializationView != null) {
					return this.objectMapper.readerWithView(deserializationView).withType(javaType).readValue(inputMessage.getBody());
				}
			}
			return this.objectMapper.readValue(inputMessage.getBody(), javaType);
		}
		catch (IOException ex) {
			throw new HttpMessageNotReadableException("Could not read document: " + ex.getMessage(), ex);
		}
	}

}
