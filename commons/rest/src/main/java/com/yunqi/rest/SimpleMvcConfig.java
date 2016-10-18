package com.yunqi.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.ClassUtils;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.JsonViewRequestBodyAdvice;
import org.springframework.web.servlet.mvc.method.annotation.JsonViewResponseBodyAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.yunqi.rest.service.BaseFilter;
import com.yunqi.rest.service.BaseMessageConverter;
import com.yunqi.rest.service.SimpleHandlerInterceptorAdapter;
import com.yunqi.rest.service.SimpleHandlerMethodReturnValueHandler;
import com.yunqi.rest.service.SimpleMethodArgumentsResolver;
import com.yunqi.rest.service.SimpleRequestMappingHandlerAdapter;

public class SimpleMvcConfig extends WebMvcConfigurationSupport {
	
	private static final boolean jackson2Present =
			ClassUtils.isPresent("com.fasterxml.jackson.databind.ObjectMapper", WebMvcConfigurationSupport.class.getClassLoader()) &&
					ClassUtils.isPresent("com.fasterxml.jackson.core.JsonGenerator", WebMvcConfigurationSupport.class.getClassLoader());

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("/static/");
	}
	
	/**
	 * 将接口的返回值包装成统一的格式，如果有异常，也会被包装成统一格式
	 * @return
	 */
	@Bean
	public HttpMessageConverter<?> customJackson2HttpMessageConverter() {
		return new BaseMessageConverter();
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(customJackson2HttpMessageConverter());
		super.addDefaultHttpMessageConverters(converters);
	}
	
	/**
	 * 添加自定义的content body json解析器，将content body json解析出来的参数放到request的attribute中，供后续使用
	 */
	@Override
	protected void addInterceptors(InterceptorRegistry registry) {
		SimpleHandlerInterceptorAdapter interceptor = new SimpleHandlerInterceptorAdapter();
		registry.addInterceptor(interceptor);
		super.addInterceptors(registry);
	}

	/**
	 * 使用自定义的方法参数解析器，从Request中取得解析好的参数
	 */
	@Override
	protected void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		SimpleMethodArgumentsResolver resolver = new SimpleMethodArgumentsResolver();
		argumentResolvers.add(resolver);
		super.addArgumentResolvers(argumentResolvers);
	}

	@Bean
	public FilterRegistrationBean myFilterRegistration() {
        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new BaseFilter());
        registrationBean.addUrlPatterns("/*");
	    return registrationBean;
	}
	
	@Override
	public RequestMappingHandlerAdapter requestMappingHandlerAdapter() {
		
		List<HandlerMethodArgumentResolver> argumentResolvers = new ArrayList<HandlerMethodArgumentResolver>();
		addArgumentResolvers(argumentResolvers);

		List<HandlerMethodReturnValueHandler> returnValueHandlers = new ArrayList<HandlerMethodReturnValueHandler>();
		addReturnValueHandlers(returnValueHandlers);

		//
		returnValueHandlers.add(new SimpleHandlerMethodReturnValueHandler());
		
		RequestMappingHandlerAdapter adapter = new SimpleRequestMappingHandlerAdapter();
		adapter.setContentNegotiationManager(mvcContentNegotiationManager());
		adapter.setMessageConverters(getMessageConverters());
		adapter.setWebBindingInitializer(getConfigurableWebBindingInitializer());
		adapter.setCustomArgumentResolvers(argumentResolvers);
		adapter.setCustomReturnValueHandlers(returnValueHandlers);

		if (jackson2Present) {
			List<RequestBodyAdvice> requestBodyAdvices = new ArrayList<RequestBodyAdvice>();
			requestBodyAdvices.add(new JsonViewRequestBodyAdvice());
			adapter.setRequestBodyAdvice(requestBodyAdvices);

			List<ResponseBodyAdvice<?>> responseBodyAdvices = new ArrayList<ResponseBodyAdvice<?>>();
			responseBodyAdvices.add(new JsonViewResponseBodyAdvice());
			adapter.setResponseBodyAdvice(responseBodyAdvices);
		}

		return adapter;
	}
	
}