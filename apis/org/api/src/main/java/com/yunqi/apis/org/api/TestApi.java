package com.yunqi.apis.org.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yunqi.rest.dto.ContentParam;

@RequestMapping("/test")
public interface TestApi{

	@RequestMapping(path="/test2", method = RequestMethod.POST)
	public void test2();
	
	@RequestMapping(path="/test3", method = RequestMethod.POST)
	public int test3();
	
	@RequestMapping(path="/test4", method = RequestMethod.POST)
	public Integer test4();
	
	@RequestMapping(path="/test5", method = RequestMethod.POST)
	public boolean test5();
	
	@RequestMapping(path="/test6", method = RequestMethod.POST)
	public String test6(@ContentParam(name="id") Long id, @ContentParam(name="name") String name);
	
}