package com.yunqi.apis.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.yunqi.apis.user.api.TestAsynApi;
import com.yunqi.apis.user.api.dto.AccountDto;
import com.yunqi.asyn.api.org.OrgTestAsynApi;
import com.yunqi.asyn.api.org.dto.OrgTestAsynDto;
import com.yunqi.asyn.exception.AsyncallException;
import com.yunqi.rest.service.ApiException;


@RestController
public class TestAsynController implements TestAsynApi{
	
	public final static Logger logger = LoggerFactory.getLogger(TestAsynController.class);
	
	@Autowired
	private OrgTestAsynApi orgTestAsynApi;

	@Override
	public AccountDto test1() {
		OrgTestAsynDto dto = new OrgTestAsynDto();
		dto.setId(1L);
		dto.setRead(true);
		dto.setName("name");
		dto = orgTestAsynApi.test1(dto);
		AccountDto a = new AccountDto();
		a.setPassword(dto.getName());
		return a;
	}
	
	@Override
	public void test2() {
		orgTestAsynApi.test2();
		System.out.println("");
	}

	@Override
	public int test3() {
		int i = orgTestAsynApi.test3();
		return i;
	}

	@Override
	public Integer test4() {
		Integer i = orgTestAsynApi.test4();
		return i;
	}

	@Override
	public boolean test5() {
		boolean b = orgTestAsynApi.test5();
		return b;
	}

	@Override
	public String test6() {
		String str = orgTestAsynApi.test6(11L, "zhangsan");
		return str;
	}

	@Override
	public boolean test7() throws ApiException {
		boolean b = false;
		try {
			b = orgTestAsynApi.test7();
		} catch (AsyncallException e) {
			e.printStackTrace();
		}
		return b;
	}

	@Override
	public void test8() throws ApiException {
		OrgTestAsynDto dto = new OrgTestAsynDto();
		dto.setId(1L);
		dto.setRead(true);
		dto.setName("name");
		orgTestAsynApi.test8(dto, 12L);
	}

}
