package com.yunqi.apis.user.dao.impl;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import com.yunqi.apis.user.dao.ResourceDao;
import com.yunqi.apis.user.domain.Resource;
import com.yunqi.core.dao.GenericDao;

@Service("resourceDao")
public class ResourceDaoImpl extends GenericDao<Resource, ObjectId> implements ResourceDao{

}
