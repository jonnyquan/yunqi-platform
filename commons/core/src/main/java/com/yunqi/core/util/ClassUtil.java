package com.yunqi.core.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import org.springframework.core.MethodParameter;

import com.yunqi.common.view.dto.ContentParam;

public class ClassUtil {
	
	public static <T extends Annotation> boolean hasParameterAnnotation(MethodParameter parameter, Class<T> annotation) throws NoSuchMethodException, SecurityException{
		return (getParameterAnnotation(parameter, annotation)!=null);
	}

	public static <T extends Annotation> T getParameterAnnotation(MethodParameter parameter, Class<T> annotation) throws NoSuchMethodException, SecurityException{
		
		Method method = parameter.getMethod();
		Class<?> clazz = method.getDeclaringClass().getInterfaces()[0];
		int size = method.getParameters().length;
		
		Method iMethod = null;
		if(size>0){
			Class<?>[] is = new Class<?>[size];
			for(int i=0; i<size; i++){
				Parameter p = method.getParameters()[i];
				is[i] = p.getType();
			}
			iMethod = clazz.getMethod(method.getName(), is);
		}else{
			iMethod = clazz.getMethod(method.getName());
		}
		
		Parameter[] ps = iMethod.getParameters();
		
		ContentParam cp = null;		
		if(ps!=null && ps.length>0){
			for(Parameter p : ps){
				cp = p.getAnnotation(ContentParam.class);
				if(cp!=null) break;
			}
		}
		
		return (T) cp;
	}
	
}
