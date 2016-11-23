package com.yunqi.apis.user.dao;

import com.yunqi.apis.user.domain.Account;
import com.yunqi.core.dao.IGenericDao;
import org.bson.types.ObjectId;

public interface AccountDao extends IGenericDao<Account, ObjectId>{

	public Account findByAccountId(String accountId);
	
}
