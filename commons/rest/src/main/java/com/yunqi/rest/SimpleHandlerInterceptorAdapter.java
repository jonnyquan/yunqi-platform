package com.yunqi.rest;

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
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.yunqi.rest.dto.ContentParam;
import com.yunqi.rest.util.ClassUtil;

import net.sf.json.JSONObject;

public class SimpleHandlerInterceptorAdapter extends HandlerInterceptorAdapter{
	
	public static String _CONTENT_PARAM_ = "_content_param_";
	
	public final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		MethodParameter[] mps = handlerMethod.getMethodParameters();
		
		boolean hasContentParam = false;
		
		if(mps!=null && mps.length>0){
			for(MethodParameter mp : mps){
				if(ClassUtil.hasParameterAnnotation(mp, ContentParam.class)){
					hasContentParam = true;
					break;
				}
			}
		}
		
		if(!hasContentParam){
			return super.preHandle(request, response, handler);
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
		
		logger.debug("body:" + body);
		
		if(body!=null && body.length()>0){
			
			JSONObject jsonObj = JSONObject.fromObject(sb.toString());
			
			Map<String, Object> map = new HashMap<String, Object>();
			
			for(MethodParameter mp : mps){
				ContentParam cp = ClassUtil.getParameterAnnotation(mp, ContentParam.class);
				if(cp==null) continue;
				
				Object value = jsonObj.get(cp.name());
				Object o = null;
				
				if(value instanceof JSONObject){
					o = jsonObj.toBean((JSONObject) jsonObj.get(cp.name()), mp.getParameterType());
				}else{
					o = value;
				}
				
				map.put(cp.name(), o);
				
			}

			request.setAttribute(_CONTENT_PARAM_, map);
		}
		
		return super.preHandle(request, response, handler);
	}

}
