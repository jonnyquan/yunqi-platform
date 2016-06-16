package com.yunqi.apis.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yunqi.apis.user.api.UserApi;
import com.yunqi.apis.user.api.dto.AccountDto;
import com.yunqi.apis.user.domain.Account;
import com.yunqi.apis.user.service.AccountService;
import com.yunqi.core.view.BaseController;

@RestController
public class UserController extends BaseController implements UserApi{
	
	@Autowired  //数据库服务类
	private AccountService accountService;
	
	@Override
	public AccountDto getAccount(@RequestBody AccountDto ac){
		Account account = accountService.findByAccountId("admin");
		AccountDto ad = new AccountDto();
		ad.setName(account.getAccountId());
		ad.setAge(13);
		return ad;
	}

}