package com.yunqi.apis.org.dao;

import org.bson.types.ObjectId;

import com.yunqi.apis.org.domain.Employee;
import com.yunqi.core.dao.IGenericDao;

public interface EmployeeDao extends IGenericDao<Employee, ObjectId>{
	
}
