package com.yunqi.apis.user.dao.impl;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.yunqi.apis.user.dao.RoleDao;
import com.yunqi.apis.user.model.Role;
import com.yunqi.core.dao.GenericDao;

@Service("roleDao")
public class RoleDaoImpl extends GenericDao<Role, ObjectId> implements RoleDao{

	@Override
	public List<Role> findRoleBySource(ObjectId id) {
		Query query = new Query().addCriteria(Criteria.where("resources").in(id));
		return mongoTemplate.find(query, Role.class);
	}

}
