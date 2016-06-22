package com.yunqi.core.util;

import java.lang.annotation.Annotation;

import org.springframework.core.MethodParameter;

public class ClassUtil {
	
	public static <T extends Annotation> boolean hasParameterAnnotation(MethodParameter parameter, Class<T> annotation){
		return (getParameterAnnotation(parameter, annotation)!=null);
	}

	public static <T extends Annotation> T getParameterAnnotation(MethodParameter parameter, Class<T> annotation){
		return parameter.getParameterAnnotation(annotation);
	}
	
}
