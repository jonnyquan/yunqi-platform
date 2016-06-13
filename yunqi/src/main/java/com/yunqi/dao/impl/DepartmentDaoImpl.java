package com.yunqi.dao.impl;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import com.yunqi.core.dao.GenericDao;
import com.yunqi.dao.DepartmentDao;
import com.yunqi.model.Department;

@Service("departmentDao")
public class DepartmentDaoImpl extends GenericDao<Department, ObjectId> implements DepartmentDao{

}
