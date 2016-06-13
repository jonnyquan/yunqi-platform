package com.yunqi.service.impl;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunqi.core.dao.IGenericDao;
import com.yunqi.core.service.GenericService;
import com.yunqi.dao.RoleDao;
import com.yunqi.model.Role;
import com.yunqi.service.RoleService;

/**
 * @author zhangguosheng
 */
@Service("roleService")
public class RoleServiceImpl extends GenericService<Role, ObjectId> implements RoleService{

	@Autowired
	private RoleDao dao;
	
	@Override
	public IGenericDao<Role, ObjectId> getDao() {
		return dao;
	}

	@Override
	public List<Role> findRoleBySource(ObjectId id) {
		return dao.findRoleBySource(id);
	}
	
}
