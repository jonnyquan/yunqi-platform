package com.yunqi.web.rest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CommonController {
	
	@RequestMapping("/login")
	public String login() {
		return "login";
	}

	@RequestMapping("/")
	public String root() {
		// 如不进行此项配置，从login登录成功后，会提示找不网页
		return "index";
	}

}
