package com.yunqi.service;

import java.util.List;

import org.bson.types.ObjectId;

import com.yunqi.core.service.IGenericService;
import com.yunqi.model.Resource;

/**
 * @author zhangguosheng
 */
public interface ResourceService extends IGenericService<Resource, ObjectId>{

	List<Resource> findAll();
	
}
