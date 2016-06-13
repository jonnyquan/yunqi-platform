package com.yunqi.service;

import java.util.List;

import org.bson.types.ObjectId;

import com.yunqi.core.service.IGenericService;
import com.yunqi.model.Role;

/**
 * @author zhangguosheng
 */
public interface RoleService extends IGenericService<Role, ObjectId>{

	public List<Role> findRoleBySource(ObjectId id);
	
}
