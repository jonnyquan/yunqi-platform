package com.yunqi.apis.user.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.yunqi.rest.service.BaseMessageConverter;
import com.yunqi.rest.service.SimpleHandlerInterceptorAdapter;
import com.yunqi.rest.service.SimpleMethodArgumentsResolver;

@Configuration
public class MvcConfig extends WebMvcConfigurationSupport {

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

}