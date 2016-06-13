package com.yunqi.apis.user.dao.impl;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.yunqi.apis.user.dao.AccountDao;
import com.yunqi.apis.user.model.Account;
import com.yunqi.core.dao.GenericDao;

@Service("accountDao")
public class AccountDaoImpl extends GenericDao<Account, ObjectId> implements AccountDao{

	@Override
	public Account findByAccountId(String accountId) {
		Criteria criatira = Criteria.where("accountId").is(accountId);
		return mongoTemplate.findOne(new Query(criatira), Account.class);
	}

}
