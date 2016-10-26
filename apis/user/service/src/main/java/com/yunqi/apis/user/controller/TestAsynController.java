package com.yunqi.apis.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.yunqi.apis.user.api.TestAsynApi;
import com.yunqi.asyn.api.org.OrgTestAsynApi;


@RestController
public class TestAsynController implements TestAsynApi{
	
	public final static Logger logger = LoggerFactory.getLogger(TestAsynController.class);
	
	@Autowired
	private OrgTestAsynApi orgTestAsynApi;
	
//	@Override
//	public AccountDto test1(AccountDto account){
//		return userTestAsynApi.test1(account);
//	}

	@Override
	public void test2() {
		orgTestAsynApi.test2();
	}

	@Override
	public int test3() {
		return 1;
	}

	@Override
	public Integer test4() {
		return 1;
	}

	@Override
	public boolean test5() {
		return true;
	}

	@Override
	public String test6(Long id, String name) {
		return name;
	}

	@Override
	public boolean test7(){
		return true;
	}

	@Override
	public void test8() {
	}

//	@Override
//	public AccountDto test9(AccountDto account, Long id){
//		return userTestAsynApi.test9(account, id);
//	}
	
}
