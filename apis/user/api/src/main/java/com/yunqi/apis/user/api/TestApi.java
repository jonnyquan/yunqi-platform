package com.yunqi.apis.user.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yunqi.apis.user.api.dto.AccountDto;
import com.yunqi.common.view.dto.ContentParam;

@RequestMapping("/test")
public interface TestApi{

	@RequestMapping(path="/req", method = RequestMethod.POST)
	public AccountDto req(@ContentParam(name="account") AccountDto account);
	
}
