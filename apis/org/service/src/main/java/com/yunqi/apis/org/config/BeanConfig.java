package com.yunqi.apis.org.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.yunqi.core.id.IdGenerator;
import com.yunqi.core.id.LongIdGenerator;

@Configuration
public class BeanConfig implements ApplicationContextAware{
	
	private static ApplicationContext applicationContext;

	public void setApplicationContext(ApplicationContext context) {
		applicationContext = context;
	}

  @SuppressWarnings("unchecked")
	public static <T> T getBean(Class<T> clazz) {
		try {
			Object bean = applicationContext.getBean(clazz);
			return (T) bean;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    }

	@Bean(name = "idGenerator")
	public IdGenerator<Long> idGenerator() throws Exception {
		return new LongIdGenerator();
	}
	
}
