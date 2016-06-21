package com.yunqi.core.view.remoting;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.sf.json.JSONObject;

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

	public static <T> T convertToBean(Class<T> clazz, String json) { 
		T obj = null;
		try {
			JSONObject jsonObj = JSONObject.fromObject(json);
			Object o = jsonObj.get("result");
//			JSONObject result = (JSONObject) jsonObj.get("result");
			obj = objectMapper.readValue(o.toString(), clazz);
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
