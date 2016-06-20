package com.yunqi.apis.user.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yunqi.apis.user.api.dto.AccountDto;

@RequestMapping("/account")
public interface AccountApi{

	@RequestMapping(path="/findByAccountId", method = RequestMethod.POST)
	public AccountDto findByAccountId(String accountId);

}
