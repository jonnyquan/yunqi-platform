package com.yunqi.apis.user.dao;

import org.bson.types.ObjectId;

import com.yunqi.apis.user.model.User;
import com.yunqi.core.dao.IGenericDao;

public interface UserDao extends IGenericDao<User, ObjectId>{
	
}
