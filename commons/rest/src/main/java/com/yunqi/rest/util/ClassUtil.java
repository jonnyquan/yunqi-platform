package com.yunqi.rest.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import org.springframework.core.MethodParameter;

import com.yunqi.rest.dto.ContentParam;

public class ClassUtil {
	
	public static <T extends Annotation> boolean hasParameterAnnotation(MethodParameter parameter, Class<T> annotation) {
		return (getParameterAnnotation(parameter, annotation)!=null);
	}

	public static <T extends Annotation> T getParameterAnnotation(MethodParameter parameter, Class<T> annotation) {
		
		Method method = parameter.getMethod();
		
		Class<?>[] clazzes = method.getDeclaringClass().getInterfaces();
		
		if(clazzes==null || clazzes.length<1) return null;

		int size = method.getParameters().length;
		
		Method iMethod = null;
		try {
			if(size>0){
				Class<?>[] is = new Class<?>[size];
				for(int i=0; i<size; i++){
					Parameter p = method.getParameters()[i];
					is[i] = p.getType();
				}
				iMethod = clazzes[0].getMethod(method.getName(), is);
			}else{
				iMethod = clazzes[0].getMethod(method.getName());
			}
		} catch (Exception e) {
			return null;
		}
		
		Parameter[] ps = iMethod.getParameters();
		
		ContentParam cp = null;		
		if(ps!=null && ps.length>=parameter.getParameterIndex()){
			cp = ps[parameter.getParameterIndex()].getAnnotation(ContentParam.class);
		}
		
		return (T) cp;
	}
	
}
