package com.yunqi.apis.user.service;

import java.util.List;

import org.bson.types.ObjectId;

import com.yunqi.apis.user.domain.Role;
import com.yunqi.core.service.IGenericService;

/**
 * @author zhangguosheng
 */
public interface RoleService extends IGenericService<Role, ObjectId>{

	public List<Role> findRoleBySource(ObjectId id);
	
}
