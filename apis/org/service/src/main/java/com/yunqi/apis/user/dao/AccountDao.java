package com.yunqi.apis.user.dao;

import org.bson.types.ObjectId;

import com.yunqi.apis.user.domain.Account;
import com.yunqi.core.dao.IGenericDao;

public interface AccountDao extends IGenericDao<Account, ObjectId>{
	
	public Account findByAccountId(String accountId);
	
}
