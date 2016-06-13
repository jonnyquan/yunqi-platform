package com.yunqi.apis.user.dao;

import java.util.List;

import org.bson.types.ObjectId;

import com.yunqi.apis.user.model.Role;
import com.yunqi.core.dao.IGenericDao;

public interface RoleDao extends IGenericDao<Role, ObjectId>{

	public List<Role> findRoleBySource(ObjectId id);

}
