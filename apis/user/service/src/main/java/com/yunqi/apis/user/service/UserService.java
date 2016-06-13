package com.yunqi.apis.user.service;

import org.bson.types.ObjectId;

import com.yunqi.apis.user.domain.User;
import com.yunqi.core.service.IGenericService;

/**
 * @author zhangguosheng
 */
public interface UserService extends IGenericService<User, ObjectId>{

//	public User findByUsername(String username);
	
}
