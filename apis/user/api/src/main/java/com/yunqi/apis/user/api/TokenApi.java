package com.yunqi.apis.user.api;

import com.earven.rest.service.RestView;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.earven.rest.dto.ContentParam;
import com.earven.rest.service.RestException;
import com.yunqi.apis.user.api.dto.TokenDto;

@RequestMapping
public interface TokenApi{

	@RestView
	@RequestMapping(path="/token", method = RequestMethod.POST)
	public TokenDto token(@ContentParam(name="accountId") String accountId, @ContentParam(name="password") String password) throws RestException;
	
}
