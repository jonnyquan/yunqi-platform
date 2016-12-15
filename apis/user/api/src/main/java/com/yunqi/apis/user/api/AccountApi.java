package com.yunqi.apis.user.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.earven.rest.dto.ContentParam;
import com.earven.rest.service.ApiException;
import com.yunqi.apis.user.api.dto.AccountDto;

@RequestMapping("/account")
public interface AccountApi{

	@RequestMapping(path="/findByAccountId", method = RequestMethod.POST)
	public AccountDto findByAccountId(@ContentParam(name = "accountId") String accountId) throws ApiException;

}
