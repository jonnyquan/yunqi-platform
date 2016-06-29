package com.yunqi.apis.user.controller;

import org.springframework.web.bind.annotation.RestController;

import com.yunqi.apis.user.api.TestApi;
import com.yunqi.apis.user.api.dto.AccountDto;
import com.yunqi.rest.BaseController;

@RestController
public class TestController extends BaseController implements TestApi{

	@Override
	public AccountDto req(AccountDto account){
    	System.out.println("ok");
    	account.setPassword("asdss");
		return account;
	}
	
}
