package com.yunqi.service.impl;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunqi.core.dao.IGenericDao;
import com.yunqi.core.service.GenericService;
import com.yunqi.dao.DepartmentDao;
import com.yunqi.model.Department;
import com.yunqi.service.DepartmentService;

/**
 * @author zhangguosheng
 */
@Service("departmentService")
public class DepartmentServiceImpl extends GenericService<Department, ObjectId> implements DepartmentService{

	@Autowired
	private DepartmentDao dao;
	
	@Override
	public IGenericDao<Department, ObjectId> getDao() {
		return dao;
	}
	
}
