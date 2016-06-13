package com.yunqi.apis.user.dao.impl;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import com.yunqi.apis.user.dao.DepartmentDao;
import com.yunqi.apis.user.model.Department;
import com.yunqi.core.dao.GenericDao;

@Service("departmentDao")
public class DepartmentDaoImpl extends GenericDao<Department, ObjectId> implements DepartmentDao{

}
