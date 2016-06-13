package com.yunqi.apis.user.service.impl;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunqi.apis.user.dao.ResourceDao;
import com.yunqi.apis.user.domain.Resource;
import com.yunqi.apis.user.service.ResourceService;
import com.yunqi.core.dao.IGenericDao;
import com.yunqi.core.service.GenericService;

/**
 * @author zhangguosheng
 */
@Service("resourceService")
public class ResourceServiceImpl extends GenericService<Resource, ObjectId> implements ResourceService{

	@Autowired
	private ResourceDao dao;
	
	@Override
	public IGenericDao<Resource, ObjectId> getDao() {
		return dao;
	}
	
	@Override
	public List<Resource> findAll() {
		return dao.findAll();
	}
	
}
