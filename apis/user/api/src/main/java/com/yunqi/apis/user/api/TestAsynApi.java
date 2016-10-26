package com.yunqi.apis.user.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yunqi.apis.user.api.dto.AccountDto;
import com.yunqi.rest.service.ApiException;

@RequestMapping("/testasyn")
public interface TestAsynApi{

	@RequestMapping(path="/test1", method = RequestMethod.POST)
	public AccountDto test1();
	
	@RequestMapping(path="/test2", method = RequestMethod.POST)
	public void test2();
	
	@RequestMapping(path="/test3", method = RequestMethod.POST)
	public int test3();
	
	@RequestMapping(path="/test4", method = RequestMethod.POST)
	public Integer test4();
	
	@RequestMapping(path="/test5", method = RequestMethod.POST)
	public boolean test5();
	
	@RequestMapping(path="/test6", method = RequestMethod.POST)
	public String test6();
	
	@RequestMapping(path="/test7", method = RequestMethod.POST)
	public boolean test7() throws ApiException;
	
	@RequestMapping(path="/test8", method = RequestMethod.POST)
	public void test8() throws ApiException;
	
}
