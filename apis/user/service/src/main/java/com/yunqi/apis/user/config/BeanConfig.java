package com.yunqi.apis.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.yunqi.core.id.IdGenerator;
import com.yunqi.core.id.LongIdGenerator;

@Configuration
public class BeanConfig {
	
	@Bean(name="idGenerator")
	public IdGenerator<Long> idGenerator() throws Exception {
		return new LongIdGenerator();
	}
	
//	@Bean
//	public EmbeddedServletContainerCustomizer containerCustomizer() {
//		return new MyCustomizer();
//	}
//
//	private static class MyCustomizer implements EmbeddedServletContainerCustomizer {
//		@Override
//		public void customize(ConfigurableEmbeddedServletContainer container) {
//			container.addErrorPages(new ErrorPage(HttpStatus.BAD_REQUEST, "/400"));
//		}
//	}

}


