package com.yunqi.apis.org.service.impl;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunqi.apis.org.dao.EmployeeDao;
import com.yunqi.apis.org.domain.Employee;
import com.yunqi.apis.org.service.EmployeeService;
import com.yunqi.core.dao.IGenericDao;
import com.yunqi.core.service.GenericService;

/**
 * @author zhangguosheng
 */
@Service("employeeService")
public class EmployeeServiceImpl extends GenericService<Employee, ObjectId> implements EmployeeService{

	@Autowired
	private EmployeeDao dao;
	
	@Override
	public IGenericDao<Employee, ObjectId> getDao() {
		return dao;
	}
	
}
