package com.yunqi.apis.user.service;

import org.bson.types.ObjectId;

import com.yunqi.apis.user.model.Account;
import com.yunqi.core.service.IGenericService;

/**
 * @author zhangguosheng
 */
public interface AccountService extends IGenericService<Account, ObjectId>{

	public Account findByAccountId(String accountId);
	
}
