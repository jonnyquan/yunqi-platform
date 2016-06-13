package com.yunqi.service.impl;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunqi.core.dao.IGenericDao;
import com.yunqi.core.service.GenericService;
import com.yunqi.dao.EmployeeDao;
import com.yunqi.model.Employee;
import com.yunqi.service.EmployeeService;

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
