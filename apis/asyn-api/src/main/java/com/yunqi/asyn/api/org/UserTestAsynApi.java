package com.yunqi.asyn.api.org;

import com.yunqi.asyn.api.org.dto.AccountDto;

public interface UserTestAsynApi {

	public AccountDto test1(AccountDto account);
	
	public void test2();
	
	public int test3();
	
	public Integer test4();
	
	public boolean test5();
	
	public String test6(Long id, String name);
	
	public boolean test7();
	
	public void test8();
	
	public AccountDto test9(AccountDto account, Long id);
	
}
