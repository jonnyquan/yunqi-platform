package com.yunqi.web.rest.controller;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {

	@Value("${application.message:Hello World}")
	private String message = "Hello World";
	
//	@Autowired
//	private TestApi testApi;

	@RequestMapping("/welcome")
	public String welcome(Map<String, Object> model) {
		model.put("time", new Date());
		model.put("message", this.message);
		return "welcome";
	}
	
//	public AccountDto test1(AccountDto account){
//		AccountDto d = testApi.test1(account);
//		return d;
//	}
//
//	public void test2() {
//		testApi.test2();
//	}
//
//	public int test3() {
//		return testApi.test3();
//	}
//
//	public Integer test4() {
//		Integer i = testApi.test4();
//		return i;
//	}
//
//	public boolean test5() {
//		boolean f = testApi.test5();
//		return f;
//	}
//
//	public String test6(Long id, String name) {
//		String s = testApi.test6(id, name);
//		return s;
//	}

}
