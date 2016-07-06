package com.yunqi.apis.org.dao.impl;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import com.yunqi.apis.org.dao.CompanyDao;
import com.yunqi.apis.org.domain.Company;
import com.yunqi.core.dao.GenericDao;

@Service("companyDao")
public class CompanyDaoImpl extends GenericDao<Company, ObjectId> implements CompanyDao{

}
