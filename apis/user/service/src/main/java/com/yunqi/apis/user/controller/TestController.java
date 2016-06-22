package com.yunqi.apis.user.controller;

import org.springframework.web.bind.annotation.RestController;

import com.yunqi.apis.user.api.TestApi;
import com.yunqi.apis.user.api.dto.AccountDto;
import com.yunqi.common.view.dto.ContentParam;
import com.yunqi.core.view.BaseController;

@RestController
public class TestController extends BaseController implements TestApi{

	@Override
	public AccountDto req(@ContentParam(name="account") AccountDto account){
    		System.out.println("ok");
		return null;
	}
	
}
