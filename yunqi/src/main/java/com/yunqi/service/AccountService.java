package com.yunqi.service;

import org.bson.types.ObjectId;

import com.yunqi.core.service.IGenericService;
import com.yunqi.model.Account;

/**
 * @author zhangguosheng
 */
public interface AccountService extends IGenericService<Account, ObjectId>{

	public Account findByAccountId(String accountId);
	
}
