package com.yunqi.rest.service;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.filter.GenericFilterBean;

import com.yunqi.rest.dto.ExceptionDto;
import com.yunqi.rest.dto.ResponseDto;
import com.yunqi.rest.dto.ResponseState;

public class BaseFilter extends GenericFilterBean {
	
	public final static String AUTH_FAILURE = "AUTH_FAILURE";
	
	private final static String ACCESS_TOKEN_KEY = "accessToken";
	
	private StringRedisTemplate redisTemplate;
	
	//访问路径匹配器
	private PathMatcher matcher = new AntPathMatcher();
	
	//需要token才能访问的接口
	private String[] authorizePath = {};

	//不需要token就可以访问的接口
	private String[] authorizeIgnoringPath = {};

    @Override
    public void doFilter(final ServletRequest req, final ServletResponse res, final FilterChain chain) {
    	
    	Date now = new Date();
    	HttpServletResponse response= (HttpServletResponse) res;
    	response.addHeader("TIME", now.getTime() + "");
    	
    	setOrigin(response);

    	boolean needAuthorize = needAuthorize((HttpServletRequest) req);
    	boolean isAuthorize = false;
    	
    	//需要认证
    	if(needAuthorize){
    		String accessToken = req.getParameter(ACCESS_TOKEN_KEY);
    		if(accessToken!=null){
    			isAuthorize = redisTemplate.execute(new RedisCallback<Boolean>() {
    				@Override
    				public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
    	                return connection.exists(accessToken.getBytes());
    				}
    	        });
    		}
    	}

        try {
        	//需要认证并且认证失败
        	if(needAuthorize && !isAuthorize) throw new RestException(AUTH_FAILURE,"Authentication failure!");
        	check(req, res);
			chain.doFilter(req, res);
		} catch (Exception ex) {
			proceessException(res, ex);
			logger.error(ex.getMessage(), ex);
		}
    }
    
    //几角CORS,跨域请求问题
    private void setOrigin(HttpServletResponse response){
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "*");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "Content-Type,X-Requested-With,accept,Origin,Access-Control-Request-Method,Access-Control-Request-Headers");
    }
    
    private boolean needAuthorize(HttpServletRequest request) {
    	String requestPath = request.getServletPath();
    	boolean needAuthorize = false;
    	//检测路径是否能够匹配上需要认证的地址列表
    	for(String p : authorizePath){
    		if(matcher.match(p, requestPath)){
    			needAuthorize = true;
    			break;
    		}
    	}
    	
    	//匹配上需要认证的路径后，检测是否是忽略的路径
    	if(needAuthorize){
    		for(String p : authorizeIgnoringPath){
    			if(matcher.match(p, requestPath)){
    				needAuthorize = false;
    				break;
    			}
    		}
    	}
		return needAuthorize;
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
    
	public void setRedisTemplate(StringRedisTemplate redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	public void authorizePath(String... path) {
		this.authorizePath = path;
	}

	public void authorizeIgnoringPath(String... path) {
		this.authorizeIgnoringPath = path;
	}
	
}
