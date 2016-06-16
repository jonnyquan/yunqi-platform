package com.yunqi.rest.controller;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yunqi.apis.user.api.UserApi;
import com.yunqi.apis.user.api.dto.AccountDto;

@Controller
public class TestController {
	
	@Autowired  
	MongoTemplate template; 
	
//	@Autowired
	private UserApi userApi;

	@Value("${application.message:Hello World}")
	private String message = "Hello World";

	@RequestMapping("/welcome")
	public String welcome(Map<String, Object> model) {
		model.put("time", new Date());
		model.put("message", this.message);
		AccountDto ad = new AccountDto();
		ad.setName("zhangs");
		ad.setAge(14);
		userApi.getAccount(ad);
		return "welcome";
	}

	@RequestMapping("/foo")
	public String foo(Map<String, Object> model) {
		throw new RuntimeException("Foo");
	}
	
	@ResponseBody
	@RequestMapping("/testMongodb")
	public String testMongodb() {
//		template.insert(new Person("Joe", 34));
		return "ok";
	}

}
