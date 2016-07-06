package com.yunqi.apis.org.dao.impl;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import com.yunqi.apis.org.dao.EmployeeDao;
import com.yunqi.apis.org.domain.Employee;
import com.yunqi.core.dao.GenericDao;

@Service("employeeDao")
public class EmployeeDaoImpl extends GenericDao<Employee, ObjectId> implements EmployeeDao{

}
