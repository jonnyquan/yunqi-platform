package com.yunqi.apis.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yunqi.asyn.api.org.UserTestAsynApi;
import com.yunqi.asyn.api.org.dto.AccountDto;

@RestController
@RequestMapping("/testasyn")
public class TestAsynController implements UserTestAsynApi{
	
	public final static Logger logger = LoggerFactory.getLogger(TestAsynController.class);
	
	@Autowired
	private UserTestAsynApi userTestAsynApi;
	
	@Override
	@RequestMapping(path="/test1", method = RequestMethod.POST)
	public AccountDto test1(AccountDto account){
		return userTestAsynApi.test1(account);
	}

	@Override
	@RequestMapping(path="/test2", method = RequestMethod.POST)
	public void test2() {
		userTestAsynApi.test2();
	}

	@Override
	@RequestMapping(path="/test3", method = RequestMethod.POST)
	public int test3() {
		return userTestAsynApi.test3();
	}

	@Override
	@RequestMapping(path="/test4", method = RequestMethod.POST)
	public Integer test4() {
		return userTestAsynApi.test4();
	}

	@Override
	@RequestMapping(path="/test5", method = RequestMethod.POST)
	public boolean test5() {
		return userTestAsynApi.test5();
	}

	@Override
	@RequestMapping(path="/test6", method = RequestMethod.POST)
	public String test6(Long id, String name) {
		return userTestAsynApi.test6(id, name);
	}

	@Override
	@RequestMapping(path="/test7", method = RequestMethod.POST)
	public boolean test7(){
		return userTestAsynApi.test7();
	}

	@Override
	@RequestMapping(path="/test8", method = RequestMethod.POST)
	public void test8() {
		userTestAsynApi.test8();
	}

	@Override
	@RequestMapping(path="/test9", method = RequestMethod.POST)
	public AccountDto test9(AccountDto account, Long id){
		return userTestAsynApi.test9(account, id);
	}
	
}
