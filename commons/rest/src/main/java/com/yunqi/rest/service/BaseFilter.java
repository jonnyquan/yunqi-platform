package com.yunqi.rest.service;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.web.filter.GenericFilterBean;

import com.yunqi.rest.dto.ExceptionDto;

public class BaseFilter extends GenericFilterBean {

    @Override
    public void doFilter(final ServletRequest req, final ServletResponse res, final FilterChain chain) {
        try {
        	check(req, res);
			chain.doFilter(req, res);
		} catch (Exception ex) {
			proceessException(res, ex);
			logger.debug(ex.getMessage());
			if( !(ex.getCause() instanceof RestException) ){
				ex.printStackTrace();
			}
		}
    }
    
    private void check(ServletRequest request, ServletResponse response) throws Exception{
    	
		String contentType = request.getContentType();
		if(contentType==null || contentType.trim().toLowerCase().indexOf("application/json")<0){
			throw new RestException("只支持application/json请求");
		}
		
    }
    
    private void proceessException(final ServletResponse res, Exception ex){
    	ExceptionDto ed = new ExceptionDto();
    	ed.setMsg(ex.getMessage());
    	String json = BeanSerializeUtil.convertToJson(ed);
    	OutputStream out = null;
    	try {
			out = res.getOutputStream();
			out.write(json.getBytes());
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(out!=null) out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
    }

}
