package com.yunqi.apis.org.service.impl;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunqi.apis.org.dao.CompanyDao;
import com.yunqi.apis.org.domain.Company;
import com.yunqi.apis.org.service.CompanyService;
import com.yunqi.core.dao.IGenericDao;
import com.yunqi.core.service.GenericService;

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
