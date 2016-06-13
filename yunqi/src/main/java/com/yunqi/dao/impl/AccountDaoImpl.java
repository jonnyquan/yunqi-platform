package com.yunqi.dao.impl;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.yunqi.core.dao.GenericDao;
import com.yunqi.dao.AccountDao;
import com.yunqi.model.Account;

@Service("accountDao")
public class AccountDaoImpl extends GenericDao<Account, ObjectId> implements AccountDao{

	@Override
	public Account findByAccountId(String accountId) {
		Criteria criatira = Criteria.where("accountId").is(accountId);
		return mongoTemplate.findOne(new Query(criatira), Account.class);
	}

}
