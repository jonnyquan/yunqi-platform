package com.yunqi.apis.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.yunqi.core.id.IdGenerator;
import com.yunqi.core.id.LongIdGenerator;

@Configuration
public class BeanConfig{

	@Bean(name = "idGenerator")
	public IdGenerator<Long> idGenerator() throws Exception {
		return new LongIdGenerator();
	}
	
}
