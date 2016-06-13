package com.yunqi.dao.impl;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.yunqi.core.dao.GenericDao;
import com.yunqi.dao.RoleDao;
import com.yunqi.model.Role;

@Service("roleDao")
public class RoleDaoImpl extends GenericDao<Role, ObjectId> implements RoleDao{

	@Override
	public List<Role> findRoleBySource(ObjectId id) {
		Query query = new Query().addCriteria(Criteria.where("resources").in(id));
		return mongoTemplate.find(query, Role.class);
	}

}
