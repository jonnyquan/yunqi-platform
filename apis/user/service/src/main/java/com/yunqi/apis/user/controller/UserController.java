package com.yunqi.apis.user.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.yunqi.apis.user.domain.Account;
import com.yunqi.apis.user.dto.AccountDto;
import com.yunqi.apis.user.service.AccountService;

@RestController
@RequestMapping("/user")
public class UserController{
	
	@Autowired  //数据库服务类
	private AccountService accountService;
	
	@RequestMapping("/")
	public String home() {
		return "Hello	World!";
	}
	
	@ResponseBody
	@RequestMapping(path="/getAccount", method = RequestMethod.POST)
	public Account getAccount(@RequestBody AccountDto ac){
		Account account = accountService.findByAccountId("admin");
		int a = 1/0;
		return account;
	}
	
	@RequestMapping("/something")
	public ResponseEntity<String> handle(HttpEntity<byte[]> requestEntity) throws UnsupportedEncodingException {
	    long requestHeader = requestEntity.getHeaders().getFirstDate("MyRequestHeader");
	    byte[] requestBody = requestEntity.getBody();
	    HttpHeaders responseHeaders = new HttpHeaders();
	    responseHeaders.set("MyResponseHeader", "MyValue");
	    return new ResponseEntity<String>("Hello World", responseHeaders, HttpStatus.CREATED);
	}

}