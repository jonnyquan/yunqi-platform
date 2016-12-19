package com.yunqi.apis.org.config;

import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.yunqi.core.id.IdGenerator;
import com.yunqi.core.id.LongIdGenerator;
import org.springframework.core.env.Environment;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class BeanConfig implements EnvironmentAware {

	private RelaxedPropertyResolver rpr;

	@Override
	public void setEnvironment(Environment env) {
		this.rpr = new RelaxedPropertyResolver(env);
	}

	@Bean(name = "idGenerator")
	public IdGenerator<Long> idGenerator() throws Exception {
		return new LongIdGenerator();
	}

	@Bean(name="jedisPool")
	public JedisPool jedisPool() {
		String host = rpr.getProperty("earven.redis.host");
		Integer port = Integer.parseInt(rpr.getProperty("earven.redis.port"));
		Integer timeOut = Integer.parseInt(rpr.getProperty("earven.redis.timeout"));
		String password = rpr.getProperty("earven.redis.password");
		Integer database = Integer.parseInt(rpr.getProperty("earven.redis.database"));

		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(Integer.parseInt(rpr.getProperty("earven.redis.pool.max-active")));
		//控制一个pool最多有多少个状态为idle(空闲的)的jedis实例。
		config.setMaxIdle(Integer.parseInt(rpr.getProperty("earven.redis.pool.max-idle")));
		//在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
		config.setTestOnBorrow(true);

		return new JedisPool(config, host, port, timeOut, password, database);
	}

}