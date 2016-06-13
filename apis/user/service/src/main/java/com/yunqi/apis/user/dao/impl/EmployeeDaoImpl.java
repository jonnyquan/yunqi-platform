package com.yunqi.apis.user.dao.impl;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import com.yunqi.apis.user.dao.EmployeeDao;
import com.yunqi.apis.user.model.Employee;
import com.yunqi.core.dao.GenericDao;

@Service("employeeDao")
public class EmployeeDaoImpl extends GenericDao<Employee, ObjectId> implements EmployeeDao{

}
