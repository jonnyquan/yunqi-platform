package com.yunqi.dao;

import java.util.List;

import org.bson.types.ObjectId;

import com.yunqi.core.dao.IGenericDao;
import com.yunqi.model.Role;

public interface RoleDao extends IGenericDao<Role, ObjectId>{

	public List<Role> findRoleBySource(ObjectId id);

}
