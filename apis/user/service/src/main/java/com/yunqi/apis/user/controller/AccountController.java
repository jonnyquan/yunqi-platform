package com.yunqi.apis.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.earven.rest.dto.ContentParam;
import com.earven.rest.service.ApiException;
import com.earven.rest.service.ExceptionCode;
import com.yunqi.apis.user.api.AccountApi;
import com.yunqi.apis.user.api.dto.AccountDto;
import com.yunqi.apis.user.api.dto.UserDto;
import com.yunqi.apis.user.domain.Account;
import com.yunqi.apis.user.domain.User;
import com.yunqi.apis.user.service.AccountService;

@RestController
public class AccountController implements AccountApi{
	
	@Autowired
	private AccountService service;

	public static AccountDto domainToDto(Account domain){
		if(domain==null) return null;
		AccountDto dto = new AccountDto();
		dto.setAccountId(domain.getAccountId());
		dto.setPassword(domain.getPassword());
		
		User user = domain.getUser();
		if(user!=null){
			UserDto userDto = UserController.domainToDto(user);
			dto.setUser(userDto);
		}
		return dto;
	}

	@Override
	public AccountDto findByAccountId(@ContentParam(name="accountId") String accountId) throws ApiException {
		
		Account account = service.findByAccountId(accountId);
		if(account==null) throw new ApiException(ExceptionCode.ACCOUNT_ERROR, "account error");
		
		AccountDto accountDto = AccountController.domainToDto(account);

		if(account.getUser()!=null){
			UserDto userDto = UserController.domainToDto(account.getUser());
			accountDto.setUser(userDto);
		}
		
		return accountDto;
	}

}
