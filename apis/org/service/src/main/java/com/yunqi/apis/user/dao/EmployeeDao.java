package com.yunqi.apis.user.dao;

import org.bson.types.ObjectId;

import com.yunqi.apis.user.domain.Employee;
import com.yunqi.core.dao.IGenericDao;

public interface EmployeeDao extends IGenericDao<Employee, ObjectId>{
	
}
