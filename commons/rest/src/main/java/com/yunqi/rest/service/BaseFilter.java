package com.yunqi.rest.service;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.web.filter.GenericFilterBean;

import com.yunqi.rest.dto.ExceptionDto;
import com.yunqi.rest.dto.ResponseDto;
import com.yunqi.rest.dto.ResponseState;

public class BaseFilter extends GenericFilterBean {

    @Override
    public void doFilter(final ServletRequest req, final ServletResponse res, final FilterChain chain) {
        try {
        	check(req, res);
			chain.doFilter(req, res);
		} catch (Exception ex) {
			proceessException(res, ex);
		}
    }
    
    /**
     * 检查数据
     * @param request
     * @param response
     * @throws Exception
     */
    private void check(ServletRequest request, ServletResponse response) throws Exception{
    	
		String contentType = request.getContentType();
		if(contentType==null || contentType.trim().toLowerCase().indexOf("application/json")<0){
			throw new RestException(ExceptionCode.CONTEXT_TYPE_ERROR, "Only support application/json content type !");
		}
		
    }
    
    /**
     * 处理异常
     * @param res
     * @param ex
     */
    private void proceessException(final ServletResponse res, Exception ex){
    	
    	if(ex==null) return;
    	
    	if(ex instanceof RestException) {
    		processBizException(res, (RestException) ex);
    	} else if(ex.getCause() instanceof RestException){
    		processBizException(res, (RestException) ex.getCause());
    	} else {
    		processSystemException(res, ex);
    	}
   
    }
    
    /**
     * 创建业务异常返回结果
     * @param res
     * @param ex
     */
    private void processBizException(final ServletResponse res, RestException ex){
    	
    	ExceptionDto ed = new ExceptionDto();
    	ed.setCode(ex.getCode());
    	ed.setMsg(ex.getMessage());
    	
    	ResponseDto rd = new ResponseDto();
    	rd.setState(ResponseState.ERROR);
    	rd.setResult(ed);
    	
    	write(res, rd);
    }
    
    /**
     * 创建系统异常返回结果
     * @param res
     * @param ex
     */
    private void processSystemException(final ServletResponse res, Exception ex){
    	
    	ExceptionDto ed = new ExceptionDto();
    	ed.setCode(ExceptionCode.SYS_ERROR);
    	ed.setMsg(ex.getMessage());
    	
    	ResponseDto rd = new ResponseDto();
    	rd.setState(ResponseState.ERROR);
    	rd.setResult(ed);
    	
    	write(res, rd);
    }
    
    /**
     * 往客户端回写数据
     * @param res
     * @param data
     */
    private void write(final ServletResponse res, ResponseDto data){
    	String json = BeanSerializeUtil.convertToJson(data);
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
