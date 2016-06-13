package com.yunqi.apis.user.dao.impl;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import com.yunqi.apis.user.dao.UserDao;
import com.yunqi.apis.user.model.User;
import com.yunqi.core.dao.GenericDao;

@Service("userDao")
public class UserDaoImpl extends GenericDao<User, ObjectId> implements UserDao{

}
