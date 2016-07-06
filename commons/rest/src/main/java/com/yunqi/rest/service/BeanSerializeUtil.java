package com.yunqi.rest.service;

import java.io.IOException;
import java.lang.reflect.Type;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yunqi.rest.dto.ExceptionDto;

public class BeanSerializeUtil {

	private static ObjectMapper objectMapper = null;
	
	static{
		objectMapper = new ObjectMapper();
		try {
			objectMapper.getFactory().createGenerator(System.out, JsonEncoding.UTF8);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String convertToJson(Object obj) {
		String s = null;
        try {
	        s = objectMapper.writeValueAsString(obj);
		} catch (IOException e) {
			e.printStackTrace();
		}  
		return s;
	}

	public static <T> T convertToBean(Type type, Object data) { 
		T obj = null;
		try {
			if(data==null) return null;
			JavaType javaType = objectMapper.getTypeFactory().constructType(type);
			obj = objectMapper.readValue(data.toString().getBytes(), javaType);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        return obj;
	}

	public static Object convertToError(Object data) {
		Object obj = null;
		try {
			obj = objectMapper.readValue(data.toString().getBytes(), ExceptionDto.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return obj;
	}
	
}
