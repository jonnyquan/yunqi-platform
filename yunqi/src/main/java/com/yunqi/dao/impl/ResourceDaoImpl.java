package com.yunqi.dao.impl;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import com.yunqi.core.dao.GenericDao;
import com.yunqi.dao.ResourceDao;
import com.yunqi.model.Resource;

@Service("resourceDao")
public class ResourceDaoImpl extends GenericDao<Resource, ObjectId> implements ResourceDao{

}
