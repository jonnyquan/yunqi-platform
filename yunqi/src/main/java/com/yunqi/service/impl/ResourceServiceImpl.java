package com.yunqi.service.impl;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunqi.core.dao.IGenericDao;
import com.yunqi.core.service.GenericService;
import com.yunqi.dao.ResourceDao;
import com.yunqi.model.Resource;
import com.yunqi.service.ResourceService;

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
