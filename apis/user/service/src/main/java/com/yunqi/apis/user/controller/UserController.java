package com.yunqi.apis.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yunqi.apis.user.model.Account;
import com.yunqi.apis.user.service.AccountService;

@RestController("/user")
public class UserController {
	
	@Autowired  //数据库服务类
	private AccountService accountService;
	
	@RequestMapping("/")
	public String home() {
		return "Hello	World!";
	}
	
	@RequestMapping("/getAccount")
	public Account getAccount(){
		Account account = accountService.findByAccountId("admin");
		return account;
	}

}