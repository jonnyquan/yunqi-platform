package com.yunqi.apis.org.asyn.api;

import org.springframework.stereotype.Service;

import com.yunqi.asyn.api.org.OrgTestAsynApi;

@Service
public class TestAsynApi implements OrgTestAsynApi{

	@Override
	public void test2() {
		System.out.println(1);
	}

}
