package com.yunqi.dao.impl;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import com.yunqi.core.dao.GenericDao;
import com.yunqi.dao.CompanyDao;
import com.yunqi.model.Company;

@Service("companyDao")
public class CompanyDaoImpl extends GenericDao<Company, ObjectId> implements CompanyDao{

}
