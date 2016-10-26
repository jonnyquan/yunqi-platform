package com.yunqi.apis.org.asyn.api;

import org.springframework.stereotype.Service;

import com.yunqi.asyn.api.org.OrgTestAsynApi;
import com.yunqi.asyn.api.org.dto.OrgTestAsynDto;
import com.yunqi.asyn.exception.AsyncallException;

@Service
public class TestAsynApi implements OrgTestAsynApi{

	@Override
	public OrgTestAsynDto test1(OrgTestAsynDto dto) {
		dto.setName("namex");
		return dto;
	}
	
	@Override
	public void test2() {
		System.out.println(1);
	}

	@Override
	public int test3() {
		return 0;
	}

	@Override
	public Integer test4() {
		return 1;
	}

	@Override
	public boolean test5() {
		return false;
	}

	@Override
	public String test6(Long id, String name) {
		return "ok";
	}

	@Override
	public boolean test7() throws AsyncallException {
		throw new AsyncallException();
	}

	@Override
	public OrgTestAsynDto test8(OrgTestAsynDto dto, Long id) throws Exception{
		int x = 1/0;
		System.out.println(x);
		return dto;
	}

}
