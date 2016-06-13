package com.yunqi.apis.user.service;

import java.util.List;

import org.bson.types.ObjectId;

import com.yunqi.apis.user.model.Resource;
import com.yunqi.core.service.IGenericService;

/**
 * @author zhangguosheng
 */
public interface ResourceService extends IGenericService<Resource, ObjectId>{

	List<Resource> findAll();
	
}
