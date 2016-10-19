package com.yunqi.apis.user.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RestController;

import com.yunqi.apis.user.api.UserTestApi;
import com.yunqi.apis.user.api.dto.AccountDto;
import com.yunqi.rest.service.ApiException;

@RestController
public class TestController implements UserTestApi{
	
	public final static Logger logger = LoggerFactory.getLogger(TestController.class);
	
	@Autowired
	private StringRedisTemplate redisTemplate;
	
	@Override
	public AccountDto test1(AccountDto account){
		logger.debug("test1");
    	account.setPassword("asdss");
		return account;
	}

	@Override
	public void test2() {
		logger.debug("test2");
	}

	@Override
	public int test3() {
		logger.debug("test3");
		return 0;
	}

	@Override
	public Integer test4() {
		logger.debug("test4");
		return null;
	}

	@Override
	public boolean test5() {
		logger.debug("test5");
		return false;
	}

	@Override
	public String test6(Long id, String name) {
		Boolean f = redisTemplate.execute(new RedisCallback<Boolean>() {
			@Override
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				Date noe = new Date();
				String key = "test";
                connection.set(key.getBytes(), noe.toString().getBytes());
                connection.expire(key.getBytes(), 100000);
                return true;
			}
        });
		logger.debug("test6 " + f);
		return "test6";
	}

	@Override
	public boolean test7() throws ApiException {
		throw new ApiException("1", "test exception");
	}

	@Override
	public void test8() {
		int x = 1/0;
		System.out.println(x);
	}

	@Override
	public AccountDto test9(AccountDto account, Long id){
		logger.debug("test1");
    	account.setPassword("asdss");
		return account;
	}

	@Override
	public AccountDto test10(AccountDto account, Long id) throws ApiException{
		throw new ApiException("1", "test exception");
	}
	
}
