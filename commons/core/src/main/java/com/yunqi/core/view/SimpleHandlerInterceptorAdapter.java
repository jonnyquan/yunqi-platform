package com.yunqi.core.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.yunqi.common.view.dto.ContentParam;

import net.sf.json.JSONObject;

public class SimpleHandlerInterceptorAdapter extends HandlerInterceptorAdapter{
	
	public static String _CONTENT_PARAM_ = "_content_param_";

//	@Override
//	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//		
//		StringBuilder sb = new StringBuilder("");
//		BufferedReader br = null;
//		try {
//			InputStream is = request.getInputStream();
//			if (is != null) {
//				br = new BufferedReader(new InputStreamReader(is));
//				String lines;
//				while ((lines = br.readLine()) != null) {
//					sb.append(lines);
//				}
//			}
//		} catch (IOException ex) {
//			ex.printStackTrace();
//		}
//		
//		String body = sb.toString();
//		
//		if(body!=null && body.length()>0){
//			
//			JSONObject jsonObj = JSONObject.fromObject(sb.toString());
//			
//			Map<String, Object> map = new HashMap<>();
//			
//			HandlerMethod method = (HandlerMethod) handler;
//			MethodParameter[] ps = method.getMethodParameters();
//			
//			for(MethodParameter mp : ps){
////				ContentParam cp = mp.getParameterAnnotation(ContentParam.class);
//				ContentParam cp = ClassUtil.getParameterAnnotation(mp, ContentParam.class);
//				Object value = jsonObj.get(cp.name());
//				Object o = null;
//				
//				if(value instanceof JSONObject){
//					o = jsonObj.toBean((JSONObject) jsonObj.get(cp.name()), mp.getParameterType());
//				}else{
//					o = value;
//				}
//				
//				map.put(cp.name(), o);
//				
//			}
//
//			request.setAttribute(_CONTENT_PARAM_, map);
//		}
//		
//		return super.preHandle(request, response, handler);
//	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		Method method = handlerMethod.getMethod();
		Class<?> clazz = method.getDeclaringClass().getInterfaces()[0];

		int size = method.getParameters().length;

		Method iMethod = null;
		if(size>0){
			Class<?>[] is = new Class<?>[size];
			for(int i=0; i<size; i++){
				Parameter p = method.getParameters()[i];
				is[i] = p.getType();
			}
			iMethod = clazz.getMethod(handlerMethod.getMethod().getName(), is);
		}else{
			iMethod = clazz.getMethod(handlerMethod.getMethod().getName());
		}
		
		Parameter[] ps = iMethod.getParameters();
		
		boolean hasContentParam = false;
		
		if(ps!=null && ps.length>0){
			for(Parameter p : ps){
				ContentParam cp = p.getAnnotation(ContentParam.class);
				if(cp!=null){
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
		
		if(body!=null && body.length()>0){
			
			JSONObject jsonObj = JSONObject.fromObject(sb.toString());
			
			Map<String, Object> map = new HashMap<>();
			
			for(Parameter p : ps){
				ContentParam cp = p.getAnnotation(ContentParam.class);
				if(cp==null) continue;
				
				Object value = jsonObj.get(cp.name());
				Object o = null;
				
				if(value instanceof JSONObject){
					o = jsonObj.toBean((JSONObject) jsonObj.get(cp.name()), p.getType());
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
