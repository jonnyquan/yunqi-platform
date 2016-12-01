package com.yunqi.rest.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

public class SimpleRequestMappingHandlerAdapter extends RequestMappingHandlerAdapter{

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	protected ModelAndView handleInternal(HttpServletRequest request, HttpServletResponse response, HandlerMethod handlerMethod) throws Exception {
		
		ModelAndView mv = null;
		try {
			mv = super.handleInternal(request, response, handlerMethod);
		} catch (Exception e) {
			throwException(handlerMethod, e);
		}
		return mv;
	}
	
    /**
     * 处理异常
     */
    private void throwException(HandlerMethod handlerMethod, Exception ex) throws Exception{
    	
    	if(ex==null) return;
    	
    	if(ex instanceof ApiException) {
    		throw processBizException(handlerMethod, (ApiException) ex);
    	} else if(ex.getCause() instanceof ApiException){
    		throw processBizException(handlerMethod, (ApiException) ex.getCause());
    	} else {
    		throw ex;
    	}
   
    }
    
    /**
     * 创建业务异常返回结果
     */
    private RestException processBizException(HandlerMethod handlerMethod, ApiException ex){
    	
		Class<?>[] clazzs = handlerMethod.getBeanType().getInterfaces();
		
		StringBuffer sb = new StringBuffer();
		if(clazzs!=null&&clazzs.length>0){
			Class<?> clazz = clazzs[0];
			RequestMapping crm = clazz.getAnnotation(RequestMapping.class);
			String moduleName = "";
			if(crm!=null && crm.value().length>0) moduleName = crm.value()[0].replace("/", "_").toUpperCase();
			
			RequestMapping mrm = AnnotationUtils.findAnnotation(handlerMethod.getMethod(), RequestMapping.class);
			String matherName = "";
			if(mrm!=null && mrm.value().length>0) matherName = mrm.value()[0].replace("/", "_").toUpperCase();
			
			sb.append(moduleName).append(matherName).append("_").append(ex.getCode());
		}
    	
    	return  new RestException(sb.toString(), ex.getMessage());
    }

}
