package com.yunqi.apis.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.yunqi.apis.user.api.UserApi;
import com.yunqi.apis.user.domain.Account;
import com.yunqi.apis.user.dto.AccountDto;
import com.yunqi.apis.user.service.AccountService;
import com.yunqi.core.view.BaseController;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController implements UserApi{
	
	@Autowired  //数据库服务类
	private AccountService accountService;
	
	@RequestMapping("/")
	public String home() {
		return "Hello	World!";
	}
	
	@ResponseBody
	@RequestMapping(path="/getAccount", method = RequestMethod.POST)
	public Account getAccount(@RequestBody AccountDto ac){
		Account account = accountService.findByAccountId("admin");
		int a = 1/0;
		return account;
	}

}