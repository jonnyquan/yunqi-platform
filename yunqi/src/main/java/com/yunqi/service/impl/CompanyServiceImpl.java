package com.yunqi.service.impl;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunqi.core.dao.IGenericDao;
import com.yunqi.core.service.GenericService;
import com.yunqi.dao.CompanyDao;
import com.yunqi.model.Company;
import com.yunqi.service.CompanyService;

/**
 * @author zhangguosheng
 */
@Service("companyService")
public class CompanyServiceImpl extends GenericService<Company, ObjectId> implements CompanyService{

	@Autowired
	private CompanyDao dao;
	
	@Override
	public IGenericDao<Company, ObjectId> getDao() {
		return dao;
	}
	
}
