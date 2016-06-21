package com.yunqi.core.view.remoting;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.web.bind.annotation.RequestMapping;

import com.caucho.hessian.client.HessianProxy;
import com.caucho.hessian.client.HessianProxyFactory;

public class HttpProxy extends HessianProxy{

	private static final long serialVersionUID = 5607059789355942804L;

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
		
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setDoOutput(true);

		conn.setRequestProperty("Accept-Charset", "utf-8");
		conn.setRequestProperty("Content-Type", "application/json");

		OutputStream out = null;
		try {
			out = conn.getOutputStream();
			if(args!=null && args.length>0){
				for(Object o : args){
					String json = BeanSerializeUtil.convertToJson(o);
					out.write(json.getBytes());
				}	
			}
			out.flush();
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			if(out!=null){
				out.close();
			}
		}
		
		BufferedReader reader = null;
        StringBuffer sb = new StringBuffer("");
		try {
			reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
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
//		method.getGenericReturnType();
        Object value = null;
        if(sb.length()>0){
        	value = BeanSerializeUtil.convertToBean(method.getReturnType(), sb.toString());
        }
        
		return value;
	}

}
