package com.yunqi.apis.user.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ExceptionHandler;

import com.yunqi.apis.user.domain.Account;

public class BaseController {  
	
//	@ResponseBody
    @ExceptionHandler  
    public Account exp(HttpServletRequest request, Exception ex) {  
		Account ac = new Account();
		ac.setAccountId("ss");
		return ac;
    } 
	
} 