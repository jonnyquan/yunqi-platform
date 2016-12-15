package com.yunqi.apis.user.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.earven.rest.dto.ContentParam;
import com.earven.rest.service.RestException;
import com.yunqi.apis.user.api.dto.TokenDto;

@RequestMapping
public interface TokenApi{
	
	@RequestMapping(path="/token", method = RequestMethod.POST)
	public TokenDto token(@ContentParam(name="accountId") String accountId, @ContentParam(name="password") String password) throws RestException;
	
}
