package com.yunqi.apis.user.service.impl;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunqi.apis.user.dao.UserDao;
import com.yunqi.apis.user.model.User;
import com.yunqi.apis.user.service.UserService;
import com.yunqi.core.dao.IGenericDao;
import com.yunqi.core.service.GenericService;

/**
 * @author zhangguosheng
 */
@Service("userService")
public class UserServiceImpl extends GenericService<User, ObjectId> implements UserService{

	@Autowired
	private UserDao dao;
	
	@Override
	public IGenericDao<User, ObjectId> getDao() {
		return dao;
	}
	
//	@Override
//	public User findByUsername(String username) {
//		if("admin".equals(username)){
//			User u = new User();
//			u.setUsername(username);
//			u.setEmail(username);
//			u.setPassword("123456");
//			u.setId(1L);
//			u.setState(UserState.ENABLE);
//			return u;
//		}
//		return null;
//	}
	
}
