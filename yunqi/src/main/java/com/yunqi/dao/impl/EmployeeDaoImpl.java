package com.yunqi.dao.impl;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import com.yunqi.core.dao.GenericDao;
import com.yunqi.dao.EmployeeDao;
import com.yunqi.model.Employee;

@Service("employeeDao")
public class EmployeeDaoImpl extends GenericDao<Employee, ObjectId> implements EmployeeDao{

}
