package com.yunqi.apis.user.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yunqi.apis.user.api.dto.AccountDto;

@RequestMapping("/user")
public interface UserApi {

	@ResponseBody
	@RequestMapping(path="/getAccount", method = RequestMethod.POST)
	public AccountDto getAccount(AccountDto ac);
	
}
