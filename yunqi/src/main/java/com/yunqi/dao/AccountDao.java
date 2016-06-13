package com.yunqi.dao;

import org.bson.types.ObjectId;

import com.yunqi.core.dao.IGenericDao;
import com.yunqi.model.Account;

public interface AccountDao extends IGenericDao<Account, ObjectId>{
	
	public Account findByAccountId(String accountId);
	
}
