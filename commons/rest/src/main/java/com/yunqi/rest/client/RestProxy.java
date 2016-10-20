package com.yunqi.rest.client;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RootUriTemplateHandler;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.yunqi.rest.dto.ContentParam;
import com.yunqi.rest.dto.ExceptionDto;
import com.yunqi.rest.dto.ResponseState;
import com.yunqi.rest.service.ApiException;
import com.yunqi.rest.service.BeanSerializeUtil;

import net.sf.json.JSONObject;

public class RestProxy implements InvocationHandler, Serializable{

	private static final long serialVersionUID = 5607059789355942804L;
	
	public final Logger logger = LoggerFactory.getLogger(getClass());

	private final RestTemplate restTemplate;
	
	private final TokenProvider tokenProvider;
	
	public RestProxy(RestTemplate restTemplate, TokenProvider tokenProvider){
		this.restTemplate = restTemplate;
		this.tokenProvider = tokenProvider;
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		
		RequestMapping crm = method.getDeclaringClass().getAnnotation(RequestMapping.class);
		if(crm ==null){
			throw new Exception("can't find api content, please set @RequestMapping at api class.");
		}
		
		String content = null;
		if(crm.value().length>0){
			content = crm.value()[0];
		}else{
			content = "";
		}
		
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
				sbOut.append("\"" + cp.name() + "\":").append(json).append(",");
			}
			int index = sbOut.lastIndexOf(",");
			if(index>-1){
				sbOut.delete(index, index+1);
			}
		}
		sbOut.append("}");
		
		RootUriTemplateHandler rootUri = (RootUriTemplateHandler) restTemplate.getUriTemplateHandler();


		
		HttpHeaders headers =new HttpHeaders();
		MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
		headers.setContentType(type);
		headers.add("Accept", MediaType.APPLICATION_JSON.toString());
		   
		HttpEntity<String> request = new HttpEntity<String>(sbOut.toString(), headers);
		
		String realPath = content + path;
		
		String accessToken = tokenProvider!=null ? tokenProvider.getAccessToken(): null;
		if(accessToken!=null){
			realPath += "?accessToken=" + accessToken;
		}
		
		logger.debug("url:" + rootUri.getRootUri() + realPath);
		logger.debug("Request:" + sbOut.toString());
		
		String r = restTemplate.postForObject(realPath, request, String.class);
		
		logger.debug("Response:" + r);
		
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
        	throw new ApiException(e.getCode(), e.getMsg());
        }
        
		return value;
	}

}
