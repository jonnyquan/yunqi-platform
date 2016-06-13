package com.yunqi.apis.user.service.impl;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunqi.apis.user.dao.RoleDao;
import com.yunqi.apis.user.domain.Role;
import com.yunqi.apis.user.service.RoleService;
import com.yunqi.core.dao.IGenericDao;
import com.yunqi.core.service.GenericService;

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
