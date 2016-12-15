package com.yunqi.apis.user.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.earven.rest.dto.ContentParam;
import com.earven.rest.service.ApiException;
import com.yunqi.apis.user.api.dto.AccountDto;

@RequestMapping("/test")
public interface UserTestApi{

	@RequestMapping(path="/test1", method = RequestMethod.POST)
	public AccountDto test1(@ContentParam(name="account") AccountDto account);
	
	@RequestMapping(path="/test2", method = RequestMethod.POST)
	public void test2();
	
	@RequestMapping(path="/test3", method = RequestMethod.POST)
	public int test3();
	
	@RequestMapping(path="/test4", method = RequestMethod.POST)
	public Integer test4();
	
	@RequestMapping(path="/test5", method = RequestMethod.POST)
	public boolean test5();
	
	@RequestMapping(path="/test6", method = RequestMethod.POST)
	public String test6(@ContentParam(name="id") Long id, @ContentParam(name="name", notnull=false) String name);
	
	@RequestMapping(path="/test7", method = RequestMethod.POST)
	public boolean test7() throws ApiException;
	
	@RequestMapping(path="/test8", method = RequestMethod.POST)
	public void test8() throws ApiException;
	
	@RequestMapping(path="/test9", method = RequestMethod.POST)
	public AccountDto test9(@ContentParam(name="account") AccountDto account, @ContentParam(name="id") Long id);
	
	@RequestMapping(path="/test10", method = RequestMethod.POST)
	public AccountDto test10(@ContentParam(name="account") AccountDto account, @ContentParam(name="id") Long id) throws ApiException;
	
	@RequestMapping(path="/test11", method = RequestMethod.POST)
	public boolean test11();
	
}
