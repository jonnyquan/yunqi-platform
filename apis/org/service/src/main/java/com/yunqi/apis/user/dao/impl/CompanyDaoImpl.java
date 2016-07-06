package com.yunqi.apis.user.dao.impl;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import com.yunqi.apis.user.dao.CompanyDao;
import com.yunqi.apis.user.domain.Company;
import com.yunqi.core.dao.GenericDao;

@Service("companyDao")
public class CompanyDaoImpl extends GenericDao<Company, ObjectId> implements CompanyDao{

}
