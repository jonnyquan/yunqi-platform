package com.yunqi.apis.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.yunqi.asyn.api.org.UserTestAsynApi;
import com.yunqi.asyn.api.org.dto.AccountDto;

@RestController
public class TestAsynController implements UserTestAsynApi{
	
	public final static Logger logger = LoggerFactory.getLogger(TestAsynController.class);
	
	@Autowired
	private UserTestAsynApi userTestAsynApi;
	
	@Override
	public AccountDto test1(AccountDto account){
		return userTestAsynApi.test1(account);
	}

	@Override
	public void test2() {
		userTestAsynApi.test2();
	}

	@Override
	public int test3() {
		return userTestAsynApi.test3();
	}

	@Override
	public Integer test4() {
		return userTestAsynApi.test4();
	}

	@Override
	public boolean test5() {
		return userTestAsynApi.test5();
	}

	@Override
	public String test6(Long id, String name) {
		return userTestAsynApi.test6(id, name);
	}

	@Override
	public boolean test7(){
		return userTestAsynApi.test7();
	}

	@Override
	public void test8() {
		userTestAsynApi.test8();
	}

	@Override
	public AccountDto test9(AccountDto account, Long id){
		return userTestAsynApi.test9(account, id);
	}
	
}
