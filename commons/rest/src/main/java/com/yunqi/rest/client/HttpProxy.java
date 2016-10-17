package com.yunqi.rest.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;

import com.caucho.hessian.client.HessianProxy;
import com.caucho.hessian.client.HessianProxyFactory;
import com.yunqi.rest.dto.ContentParam;
import com.yunqi.rest.dto.ExceptionDto;
import com.yunqi.rest.dto.ResponseState;
import com.yunqi.rest.service.BeanSerializeUtil;

import net.sf.json.JSONObject;

/**
 * 客户的远程调用代理
 * 通过解析API接口注解，发送http请求，并且将返回的json转换成对应的实体返回给客户端
 * @author bestaone
 *
 */
public class HttpProxy extends HessianProxy{

	private static final long serialVersionUID = 5607059789355942804L;
	
	public final Logger logger = LoggerFactory.getLogger(getClass());

	public HttpProxy(URL url, HessianProxyFactory factory, Class<?> type) {
		super(url, factory, type);
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		
		RequestMapping crm = method.getDeclaringClass().getAnnotation(RequestMapping.class);
		if(crm ==null){
			throw new Exception("can't find api content, please set @RequestMapping at api class.");
		}
		String content = crm.value()[0];
		
		RequestMapping mrm = method.getAnnotation(RequestMapping.class);
		if(mrm ==null){
			throw new Exception("can't find api path, please set @RequestMapping at api method.");
		}
		String path = mrm.path()[0];
		
		URL url = new URL(this.getURL().toString() + content + path);
		
		logger.debug("url:" + this.getURL().toString() + content + path);
		
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		conn.setRequestProperty("Accept-Charset", "utf-8");
		conn.setRequestProperty("Content-Type", "application/json");
		conn.setRequestProperty("connection", "Keep-Alive");
		conn.setDoInput(true);
        conn.setDoOutput(true);
		
		StringBuffer sbOut = new StringBuffer("{");
		Parameter[] ps = method.getParameters();
		if(args!=null && args.length>0 && ps!=null && ps.length>0){
			for(int i=0; i<ps.length && i<args.length; i++){
				ContentParam cp = ps[i].getAnnotation(ContentParam.class);
				Object o = args[i];
				String json = BeanSerializeUtil.convertToJson(o);
				sbOut.append(" \"" + cp.name() + "\": ").append(json).append(",");
			}
			int index = sbOut.lastIndexOf(",");
			if(index>-1){
				sbOut.delete(index, index+1);
			}
		}
		sbOut.append(" }");
		OutputStream out = null;
		try {
			out = conn.getOutputStream();
			out.write(sbOut.toString().getBytes());
			out.flush();
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			if(out!=null){
				out.close();
			}
		}
		
		logger.debug("Request:" + sbOut.toString());
		
		BufferedReader reader = null;
        StringBuffer sb = new StringBuffer("");
		try {
			reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
			String lines;
			while ((lines = reader.readLine()) != null) {
			    lines = new String(lines.getBytes(), "utf-8");
			    sb.append(lines);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(reader!=null){
				reader.close();
			}
		}
		
		logger.debug("Response:" + sb.toString());

		JSONObject jsonObj = JSONObject.fromObject(sb.toString());
		if(jsonObj==null) return null;
		
		Object state = jsonObj.get("state");
		Object data = jsonObj.get("result");
		
        Object value = null;
        if(ResponseState.SUCCESS.name().equals(state)){
        	value = BeanSerializeUtil.convertToBean(method.getGenericReturnType(), data);
        }else{
        	value = BeanSerializeUtil.convertToError(data);
        	ExceptionDto e = (ExceptionDto) value;
        	throw new Exception(e.getMsg());
        }
        
		return value;
	}

}
