package com.yunqi.apis.user.api;

import com.earven.rest.service.RestView;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.earven.rest.service.ApiException;
import com.yunqi.apis.user.api.dto.AccountDto;

@RequestMapping("/testasyn")
public interface TestAsynApi{

	@RestView
	@RequestMapping(path="/test1", method = RequestMethod.POST)
	public AccountDto test1();

	@RestView
	@RequestMapping(path="/test2", method = RequestMethod.POST)
	public void test2();

	@RestView
	@RequestMapping(path="/test3", method = RequestMethod.POST)
	public int test3();

	@RestView
	@RequestMapping(path="/test4", method = RequestMethod.POST)
	public Integer test4();

	@RestView
	@RequestMapping(path="/test5", method = RequestMethod.POST)
	public boolean test5();

	@RestView
	@RequestMapping(path="/test6", method = RequestMethod.POST)
	public String test6();

	@RestView
	@RequestMapping(path="/test7", method = RequestMethod.POST)
	public boolean test7() throws ApiException;

	@RestView
	@RequestMapping(path="/test8", method = RequestMethod.POST)
	public void test8() throws ApiException;
	
}
