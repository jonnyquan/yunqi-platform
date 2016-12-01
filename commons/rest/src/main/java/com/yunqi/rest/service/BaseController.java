package com.yunqi.rest.service;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.yunqi.rest.dto.ExceptionDto;

public abstract class BaseController {  
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
    @ExceptionHandler  
    public ExceptionDto exception(HttpServletRequest request, Exception ex) {
    	logger.debug(ex.getMessage());
    	ExceptionDto ed = new ExceptionDto();
    	ed.setMsg(ex.getMessage());
    	
    	if(ex instanceof ApiException){
    		String code = ((ApiException) ex).getCode();
    		ed.setCode(getApiCode() + code);
    	} else {
    		ed.setCode(ExceptionCode.SYS_ERROR);
    	}
 
		return ed;
    } 
	
    public abstract Integer getApiCode();
    
} 
