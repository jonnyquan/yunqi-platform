package com.yunqi.apis.org.asyn.api;

import org.springframework.stereotype.Service;

import com.yunqi.asyn.api.org.UserTestAsynApi;
import com.yunqi.asyn.api.org.dto.AccountDto;

@Service
public class TestAsynApi implements UserTestAsynApi{

	@Override
	public AccountDto test1(AccountDto account) {
		account.setPassword("ccc");
		return account;
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
		return "TEST6";
	}

	@Override
	public boolean test7() {
		return false;
	}

	@Override
	public void test8() {
		
	}

	@Override
	public AccountDto test9(AccountDto account, Long id) {
		account.setPassword("XX");
		return account;
	}

}
