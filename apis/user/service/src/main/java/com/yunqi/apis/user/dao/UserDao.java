package com.yunqi.apis.user.dao;

import org.bson.types.ObjectId;

import com.yunqi.apis.user.domain.User;
import com.yunqi.core.dao.IGenericDao;

public interface UserDao extends IGenericDao<User, ObjectId>{
	
}
