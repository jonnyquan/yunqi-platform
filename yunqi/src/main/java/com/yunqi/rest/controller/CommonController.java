package com.yunqi.rest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CommonController {
	
//	@RequestMapping("/hello")
//	public String hello() {
//		SecurityContext ctx = SecurityContextHolder.getContext();
//		Authentication auth = ctx.getAuthentication();
//		if (auth.getPrincipal() instanceof UserDetails) {
//			SysUser user = (SysUser) auth.getPrincipal();
//			System.out.println(user.getEmail());
//		}
//		// 本段代码演示如何获取登录的用户资料
//
//		return "hello";
//	}
	
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
