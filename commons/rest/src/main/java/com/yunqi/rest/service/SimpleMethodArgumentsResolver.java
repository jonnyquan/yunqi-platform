package com.yunqi.rest.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.yunqi.rest.dto.ContentParam;
import com.yunqi.rest.util.ClassUtil;

/**
 * api方法参数解析器
 * context body json已在拦截器中被解析到了Request Attribute中，在填充参数值时这里会从此处提取值填充参数
 */
public class SimpleMethodArgumentsResolver implements HandlerMethodArgumentResolver {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	public boolean supportsParameter(MethodParameter parameter) {
		return ClassUtil.hasParameterAnnotation(parameter, ContentParam.class);
	}

	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		
		ContentParam cp = ClassUtil.getParameterAnnotation(parameter, ContentParam.class);
		String key = cp.name();
		
		Map<String, Object> map = (Map<String, Object>) webRequest.getAttribute(SimpleHandlerInterceptorAdapter.CONTENT_PARAM, RequestAttributes.SCOPE_REQUEST);
		
		Object v = (map!=null?map.get(key):null);
		
		if(cp.notnull() && v==null) throw new RestException(ExceptionCode.API_PARAM_NULL_ERROR, "Param " + key + " can not null !");

		return v;
	}

}