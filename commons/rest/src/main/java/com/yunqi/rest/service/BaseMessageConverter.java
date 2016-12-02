package com.yunqi.rest.service;

import java.io.IOException;
import java.lang.reflect.Type;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yunqi.rest.dto.ExceptionDto;
import com.yunqi.rest.dto.Null;
import com.yunqi.rest.dto.ResponseDto;
import com.yunqi.rest.dto.ResponseState;

/**
 * 接口返回值处理器
 * 会将返回值包装为ResponseDto格式，包括异常信息
 * @author bestaone
 */
public class BaseMessageConverter extends MappingJackson2HttpMessageConverter{

	private final Logger logger = LoggerFactory.getLogger(getClass());

	public BaseMessageConverter() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		objectMapper.setSerializationInclusion(Include.NON_NULL);
		this.setObjectMapper(objectMapper);
	}
	
	@Override
	public boolean canWrite(Type type, Class<?> clazz, MediaType mediaType) {
//		if(clazz.isAssignableFrom(String.class)){
//			return true;
//		}
		return super.canWrite(type, clazz, mediaType);
	}

	@Override
	protected void writeInternal(Object object, Type type, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
		
		if(object==null){
			super.writeInternal(object, type, outputMessage);
			return;
		}
		
		if(object instanceof ResponseDto){
			super.writeInternal(object, type, outputMessage);
			return;
		}
		
		ResponseDto rd = new ResponseDto();
		if(object instanceof ExceptionDto){
			rd.setState(ResponseState.ERROR);
		} else{
			rd.setState(ResponseState.SUCCESS);
		}
		
		if( !(object instanceof Null) ){
			rd.setResult(object);
		}
		
		super.writeInternal(rd, type, outputMessage);

		String json = BeanSerializeUtil.convertToJson(rd);
		logger.info("Response:{}", json);

	}

}
