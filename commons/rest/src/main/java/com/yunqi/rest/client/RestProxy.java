package com.yunqi.rest.client;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.yunqi.rest.dto.ContentParam;
import com.yunqi.rest.dto.ExceptionDto;
import com.yunqi.rest.dto.ResponseState;
import com.yunqi.rest.service.BeanSerializeUtil;

import net.sf.json.JSONObject;

public class RestProxy implements InvocationHandler, Serializable{

	private static final long serialVersionUID = 5607059789355942804L;
	
	public final Logger logger = LoggerFactory.getLogger(getClass());

	private RestTemplate restTemplate;
	
	public RestProxy(RestTemplate restTemplate){
		this.restTemplate = restTemplate;
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		
		RequestMapping crm = method.getDeclaringClass().getAnnotation(RequestMapping.class);
		if(crm ==null){
			throw new Exception("can't find api content, please set @RequestMapping at api class.");
		}
		String content = crm.value()[0];
		
		RequestMapping mrm = method.getAnnotation(RequestMapping.class);
		if(mrm ==null){
			throw new Exception("can't find api path, please set @RequestMapping at api method.");
		}
		String path = mrm.path()[0];
		
		StringBuffer sbOut = new StringBuffer("{");
		Parameter[] ps = method.getParameters();
		if(args!=null && args.length>0 && ps!=null && ps.length>0){
			for(int i=0; i<ps.length && i<args.length; i++){
				ContentParam cp = ps[i].getAnnotation(ContentParam.class);
				Object o = args[i];
				String json = BeanSerializeUtil.convertToJson(o);
				sbOut.append(" \"" + cp.name() + "\": ").append(json).append(",");
			}
			int index = sbOut.lastIndexOf(",");
			if(index>-1){
				sbOut.delete(index, index+1);
			}
		}
		sbOut.append(" }");

		logger.debug("Request:" + sbOut.toString());
		
		HttpHeaders headers =new HttpHeaders();
		MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
		headers.setContentType(type);
		headers.add("Accept", MediaType.APPLICATION_JSON.toString());
		   
		HttpEntity<String> request = new HttpEntity<String>(sbOut.toString(), headers);
		
		String r = restTemplate.postForObject(content + path, request, String.class);
		
		logger.debug("url:" + content + path);

		JSONObject jsonObj = JSONObject.fromObject(r);
		if(jsonObj==null) return null;
		
		Object state = jsonObj.get("state");
		Object data = jsonObj.get("result");
		
        Object value = null;
        if(ResponseState.SUCCESS.name().equals(state)){
        	if(method.getGenericReturnType().equals(String.class)){
        		value = data.toString();
        	} else {
        		value = BeanSerializeUtil.convertToBean(method.getGenericReturnType(), data);
        	}
        }else{
        	value = BeanSerializeUtil.convertToError(data);
        	ExceptionDto e = (ExceptionDto) value;
        	throw new Exception(e.getMsg());
        }
        
		return value;
	}

}
