package com.yunqi.rest.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

public class SimpleRequestMappingHandlerAdapter extends RequestMappingHandlerAdapter{

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
     * @param res
     * @param ex
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
     * @param res
     * @param ex
     */
    private RestException processBizException(HandlerMethod handlerMethod, ApiException ex){
    	return  new RestException(ex.getCode(), ex.getMessage());
    }

}
