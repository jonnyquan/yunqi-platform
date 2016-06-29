package com.yunqi.apis.user.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.yunqi.rest.BaseMessageConverter;
import com.yunqi.rest.SimpleHandlerInterceptorAdapter;
import com.yunqi.rest.SimpleMethodArgumentsResolver;

@Configuration
public class MvcConfig extends WebMvcConfigurationSupport {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("/static/");
	}
	
	@Bean
	public HttpMessageConverter<?> customJackson2HttpMessageConverter() {
		return new BaseMessageConverter();
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(customJackson2HttpMessageConverter());
		super.addDefaultHttpMessageConverters(converters);
	}

	@Override
	protected void addInterceptors(InterceptorRegistry registry) {
		SimpleHandlerInterceptorAdapter interceptor = new SimpleHandlerInterceptorAdapter();
		registry.addInterceptor(interceptor);
		super.addInterceptors(registry);
	}

	@Override
	protected void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		SimpleMethodArgumentsResolver resolver = new SimpleMethodArgumentsResolver();
		argumentResolvers.add(resolver);
		super.addArgumentResolvers(argumentResolvers);
	}

}