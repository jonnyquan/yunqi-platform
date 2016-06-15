package com.yunqi.apis.user.exception;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yunqi.apis.user.domain.Account;

@Component
public class MyExceptionHandler implements HandlerExceptionResolver { 
	
//	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,  Exception ex) {  
//        
////		ObjectMapper mapper = new ObjectMapper();  
//		
////		ModelAndView mv = new ModelAndView();
////        FastJsonJsonView view = new FastJsonJsonView();
////        Map<String, Object> attributes = new HashMap<String, Object>();
////        attributes.put("code", "1000001");
////        attributes.put("msg", ex.getMessage());
////        view.setAttributesMap(attributes);
////        mv.setView(view); 
//        
//        ModelAndView mv = new ModelAndView();
//        mv.setViewName("ajax"); // 返回的文件名
//        mv.addObject("message", "hello kitty");
//        
//        return mv;
//	}
	  
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,  Exception ex) {  
    	
    	if(request.getContentType().indexOf("application/json") > -1){
			try {
				ObjectMapper mapper = new ObjectMapper();
				PrintWriter writer = response.getWriter();
				Account ac = new Account();
				ac.setAccountId("ss");
				String json = mapper.writeValueAsString(ac);  
				writer.write(json);
				writer.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
    	return null;
    }  
	
    
}