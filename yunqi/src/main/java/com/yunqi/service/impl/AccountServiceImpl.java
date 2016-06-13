package com.yunqi.service.impl;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunqi.core.dao.IGenericDao;
import com.yunqi.core.service.GenericService;
import com.yunqi.dao.AccountDao;
import com.yunqi.model.Account;
import com.yunqi.service.AccountService;

/**
 * @author zhangguosheng
 */
@Service("accountService")
public class AccountServiceImpl extends GenericService<Account, ObjectId> implements AccountService{

	@Autowired
	private AccountDao dao;
	
	@Override
	public IGenericDao<Account, ObjectId> getDao() {
		return dao;
	}

	@Override
	public Account findByAccountId(String accountId) {
		return dao.findByAccountId(accountId);
	}
	
}
