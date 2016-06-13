package com.yunqi.dao.impl;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import com.yunqi.core.dao.GenericDao;
import com.yunqi.dao.UserDao;
import com.yunqi.model.User;

@Service("userDao")
public class UserDaoImpl extends GenericDao<User, ObjectId> implements UserDao{

}
