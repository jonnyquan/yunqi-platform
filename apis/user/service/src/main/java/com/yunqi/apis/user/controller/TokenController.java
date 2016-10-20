package com.yunqi.apis.user.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.web.bind.annotation.RestController;

import com.yunqi.apis.user.api.TokenApi;
import com.yunqi.apis.user.api.dto.TokenDto;
import com.yunqi.apis.user.domain.Account;
import com.yunqi.apis.user.service.AccountService;
import com.yunqi.rest.service.AuthEntity;
import com.yunqi.rest.service.RestException;
import com.yunqi.rest.util.TokenUtil;

@RestController
public class TokenController implements TokenApi{
	
	@Autowired
	private AccountService service;
	
	@Autowired
	private StringRedisTemplate redisTemplate;
	
	@Autowired
	private JdkSerializationRedisSerializer redisSerializer;

	@Override
	public TokenDto token(String accountId, String password) throws RestException{
		Account account = service.findByAccountId(accountId);
		if(account==null) throw new RestException("ACCOUNT_ERROR","Account error!");
		
		if(account.getPassword()==null || !account.getPassword().equals(password)) throw new RestException("ACCOUNT_ERROR","Account error!");
		
		String accessToken = redisTemplate.execute(new RedisCallback<String>() {
			@Override
			public String doInRedis(RedisConnection connection) throws DataAccessException {
				
				//如果这个账号的token已经存在了，则让其失效
				byte[] accountToTokenKey = (TokenUtil.ACCOUNT_TO_TOKEN_KEY + account.getId().toString()).getBytes();
				if(connection.exists(accountToTokenKey)){
					//将accessToken对于的缓存数据失效掉
					connection.expire(connection.get(accountToTokenKey), 0);
				}
				
				AuthEntity entity = new AuthEntity();
				entity.setUserId(account.getUser().getId().toString());
				entity.setAccountId(account.getId().toString());
				entity.setAccount(account.getAccountId());
				
				String accessToken = TokenUtil.TOKEY_KEY + TokenUtil.getToke();
				byte[] key = accessToken.getBytes();
				connection.setEx(accountToTokenKey, TokenUtil.TOKEY_EXPIRE_TIME, key);
				connection.setEx(key, TokenUtil.TOKEY_EXPIRE_TIME, redisSerializer.serialize(entity));
				
				return accessToken;
			}
		});
		
		TokenDto token = new TokenDto();
		token.setAccessToken(accessToken);
		token.setExpireIn(new Date().getTime());
		return token;
	}
	
}
