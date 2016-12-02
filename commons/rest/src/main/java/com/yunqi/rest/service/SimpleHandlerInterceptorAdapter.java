package com.yunqi.rest.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.yunqi.rest.dto.ContentParam;
import com.yunqi.rest.util.ClassUtil;

import net.sf.json.JSONObject;

/**
 * 拦截收到的Request，并从Request中解析出context body json，然后解析后放到Request attribute中，以供invoke api接口后，填充接口参数
 * @author bestaone
 */
public class SimpleHandlerInterceptorAdapter extends HandlerInterceptorAdapter{
	
	public static String CONTENT_PARAM = "_content_param_";
	
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		try {
			super.postHandle(request, response, handler, modelAndView);
		} catch (Exception e) {
			processException(100, e);
		}
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
				
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		MethodParameter[] mps = handlerMethod.getMethodParameters();
		
		boolean hasContentParam = false;
		
		if(mps!=null && mps.length>0){
			for(int i=0; i<mps.length; i++){
				if(ClassUtil.hasParameterAnnotation(mps[i], ContentParam.class)){
					hasContentParam = true;
					break;
				}
			}
		}
		
		if(!hasContentParam){
			boolean f = false;
			try {
				f = super.preHandle(request, response, handler);
			} catch (Exception e) {
				processException(100, e);
			}
			return f;
		}
		
		StringBuilder sb = new StringBuilder("");
		BufferedReader br = null;
		try {
			InputStream is = request.getInputStream();
			if (is != null) {
				br = new BufferedReader(new InputStreamReader(is));
				String lines;
				while ((lines = br.readLine()) != null) {
					sb.append(lines);
				}
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		String body = sb.toString();
		logger.info("Url:{}", request.getRequestURL().toString() + "?" + request.getQueryString()!=null?request.getQueryString():"");
		logger.info("Request:{}", body);
		
		if(body!=null && body.length()>0){
			
			JSONObject jsonObj = JSONObject.fromObject(sb.toString());
			
			Map<String, Object> map = new HashMap<String, Object>();
			
			for(MethodParameter mp : mps){
				ContentParam cp = ClassUtil.getParameterAnnotation(mp, ContentParam.class);
				if(cp==null) continue;
				Object value = jsonObj.get(cp.name());
				
				if(value==null){
					continue;
				}
				
				Object o = null;
				if(value instanceof JSONObject){
					o = JSONObject.toBean((JSONObject) jsonObj.get(cp.name()), mp.getParameterType());
				}else if(mp.getParameterType().isAssignableFrom(Long.class)){
					o = (value instanceof Long) ? value : Long.parseLong(value.toString());
				}else if(mp.getParameterType().isAssignableFrom(String.class)){
					o = (value instanceof String) ? value : value.toString();
				}else if(mp.getParameterType().isAssignableFrom(Integer.class)){
					o = (value instanceof Integer) ? value : Integer.parseInt(value.toString());
				}else if(mp.getParameterType().isAssignableFrom(Boolean.class)){
					o = (value instanceof Boolean) ? value : Boolean.parseBoolean(value.toString());
				}else if(mp.getParameterType().isAssignableFrom(Double.class)){
					o = (value instanceof Double) ? value : Double.parseDouble(value.toString());
				}else if(mp.getParameterType().isAssignableFrom(Float.class)){
					o = (value instanceof Float) ? value : Float.parseFloat(value.toString());
				}else if(mp.getParameterType().isAssignableFrom(Short.class)){
					o = (value instanceof Short) ? value : Short.parseShort(value.toString());
				}else if(mp.getParameterType().isAssignableFrom(Byte.class)){
					o = (value instanceof Byte) ? value : Byte.parseByte(value.toString());
				}else{
					o = value;
				}
				
				map.put(cp.name(), o);
				
			}

			request.setAttribute(CONTENT_PARAM, map);
		}
		
		boolean f = false;
		
		try {
			f = super.preHandle(request, response, handler);
		} catch (Exception e) {
			processException(100, e);
		}
		
		return f;
	}
	
	/**
	 * 解析异常
	 * @param apiCode
	 * @param e
	 * @throws Exception
	 */
	private void processException(Integer apiCode, Exception e) throws Exception{
		
		if(e==null) return;
		
		if(e instanceof ApiException){
			throw buildRestException(apiCode, (ApiException) e);
		} else if (e.getCause() instanceof ApiException){
			throw buildRestException(apiCode, (ApiException) e.getCause());
		} else if (e instanceof RestException){
			throw e;
		} else if (e.getCause() instanceof ApiException){
			throw (RestException) e.getCause();
		} else {
			throw e;
		}
		
	}
	
	/**
	 * 降api异常转换为rest异常
	 * @param apiCode
	 * @param e
	 * @return
	 */
	private RestException buildRestException(Integer apiCode, ApiException e){
		return new RestException(apiCode+e.getCode(), e.getMessage());
	}

}
