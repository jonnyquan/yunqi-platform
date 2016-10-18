package com.yunqi.web.rest.controller;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yunqi.apis.user.api.UserTestApi;
import com.yunqi.apis.user.api.dto.AccountDto;
import com.yunqi.rest.service.ApiException;

@Controller
@RequestMapping("/test")
public class TestController {

	@Value("${application.message:Hello World}")
	private String message = "Hello World";
	
	@Autowired
	private UserTestApi userTestApi;

	@RequestMapping("/welcome")
	public String welcome(Map<String, Object> model) {
		model.put("time", new Date());
		model.put("message", this.message);
		return "welcome";
	}
	
	@ResponseBody
	@RequestMapping("/test1")
	public AccountDto test1(){
		AccountDto account = new AccountDto();
		account.setAccountId("idxxx");
		account.setPassword("password");
		AccountDto d = userTestApi.test1(account);
		return d;
	}

	@ResponseBody
	@RequestMapping("/test2")
	public void test2() {
		userTestApi.test2();
	}

	@ResponseBody
	@RequestMapping("/test3")
	public int test3() {
		return userTestApi.test3();
	}

	@ResponseBody
	@RequestMapping("/test4")
	public Integer test4() {
		Integer i = userTestApi.test4();
		return i;
	}

	@ResponseBody
	@RequestMapping("/test5")
	public boolean test5() {
		boolean f = userTestApi.test5();
		return f;
	}

	@ResponseBody
	@RequestMapping("/test6")
	public String test6() {
		String s = userTestApi.test6(1L, "zhangsan");
		return s;
	}
	
	@ResponseBody
	@RequestMapping("/test7")
	public boolean test7() {
		boolean s = false;
		try {
			s = userTestApi.test7();
		} catch (ApiException e) {
			e.printStackTrace();
		}
		return s;
	}
	
	@ResponseBody
	@RequestMapping("/test8")
	public void test8() throws ApiException{
		try {
			userTestApi.test8();
		} catch (ApiException e) {
			e.printStackTrace();
		}
	}

}
