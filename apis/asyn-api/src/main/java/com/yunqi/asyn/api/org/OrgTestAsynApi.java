package com.yunqi.asyn.api.org;

import com.yunqi.asyn.api.org.dto.OrgTestAsynDto;
import com.yunqi.asyn.exception.AsyncallException;
import com.yunqi.common.asyn.AsynApi;
import com.yunqi.common.asyn.Module;

@AsynApi(module=Module.ORG)
public interface OrgTestAsynApi {

	public OrgTestAsynDto test1(OrgTestAsynDto dto);
	
	public void test2();
	
	public int test3();
	
	public Integer test4();
	
	public boolean test5();
	
	public String test6(Long id, String name);
	
	public boolean test7() throws AsyncallException;
	
	public OrgTestAsynDto test8(OrgTestAsynDto dto, Long id) throws Exception;
	
}
