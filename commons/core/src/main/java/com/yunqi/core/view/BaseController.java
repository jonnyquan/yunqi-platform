package com.yunqi.core.view;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.yunqi.core.view.dto.ExceptionDto;

public class BaseController {  
	
	public final Logger logger = LoggerFactory.getLogger(getClass());
	
    @ExceptionHandler  
    public ExceptionDto exception(HttpServletRequest request, Exception ex) {
    	logger.debug(ex.getMessage());
    	ExceptionDto ed = new ExceptionDto();
    	ed.setMsg(ex.getMessage());
		return ed;
    } 
	
} 