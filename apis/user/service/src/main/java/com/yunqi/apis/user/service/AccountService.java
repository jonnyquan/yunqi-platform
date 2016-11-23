package com.yunqi.apis.user.service;

import org.bson.types.ObjectId;

import com.yunqi.apis.user.domain.Account;
import com.yunqi.core.service.IGenericService;

import javax.cache.annotation.CacheResult;

/**
 * @author zhangguosheng
 */
public interface AccountService extends IGenericService<Account, ObjectId>{
	
	public Account findByAccountId(String accountId);
	
}
