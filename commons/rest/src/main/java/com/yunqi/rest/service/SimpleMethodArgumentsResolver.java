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
	
	public final Logger logger = LoggerFactory.getLogger(getClass());
	
	public boolean supportsParameter(MethodParameter parameter) {
		boolean flag = false;
		try {
			flag = ClassUtil.hasParameterAnnotation(parameter, ContentParam.class);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		return flag;
	}

	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		
		Map<String, Object> map = (Map<String, Object>) webRequest.getAttribute(SimpleHandlerInterceptorAdapter.CONTENT_PARAM, RequestAttributes.SCOPE_REQUEST);
		ContentParam cp = ClassUtil.getParameterAnnotation(parameter, ContentParam.class);
		String key = cp.name();

		return map.get(key);
	}

}