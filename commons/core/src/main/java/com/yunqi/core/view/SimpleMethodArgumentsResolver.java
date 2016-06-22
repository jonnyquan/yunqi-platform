package com.yunqi.core.view;

import java.util.Map;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.yunqi.common.view.dto.ContentParam;

public class SimpleMethodArgumentsResolver implements HandlerMethodArgumentResolver {
	
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.hasParameterAnnotation(ContentParam.class);
	}

	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		
		Map<String, Object> map = (Map<String, Object>) webRequest.getAttribute(SimpleHandlerInterceptorAdapter._CONTENT_PARAM_, RequestAttributes.SCOPE_REQUEST);
		ContentParam cp = parameter.getParameterAnnotation(ContentParam.class);
		String key = cp.name();
		
		return map.get(key);
	}
	
//	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
//		
//		Map<String, Object> map = (Map<String, Object>) webRequest.getAttribute(SimpleHandlerInterceptorAdapter._CONTENT_PARAM_, RequestAttributes.SCOPE_REQUEST);
//		
//		Method method = parameter.getMethod();
//		Class<?> clazz = method.getDeclaringClass().getInterfaces()[0];
//
//		int size = method.getParameters().length;
//
//		Method iMethod = null;
//		if(size>0){
//			Class<?>[] is = new Class<?>[size];
//			for(int i=0; i<size; i++){
//				Parameter p = method.getParameters()[i];
//				is[i] = p.getType();
//			}
//			iMethod = clazz.getMethod(method.getName(), is);
//		}else{
//			iMethod = clazz.getMethod(method.getName());
//		}
//		
//		Parameter[] ps = iMethod.getParameters();
//		
//		String key = null;
//		
//		if(ps!=null && ps.length>0){
//			for(Parameter p : ps){
//				ContentParam cp = p.getAnnotation(ContentParam.class);
//				if(cp!=null){
//					key = cp.name();
//					break;
//				}
//			}
//		}
//		
//		return map.get(key);
//	}
	
}