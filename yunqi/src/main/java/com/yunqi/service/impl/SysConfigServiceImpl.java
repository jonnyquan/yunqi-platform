package com.yunqi.service.impl;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunqi.core.dao.IGenericDao;
import com.yunqi.core.service.GenericService;
import com.yunqi.dao.SysConfigDao;
import com.yunqi.model.SysConfig;
import com.yunqi.service.SysConfigService;

/**
 * @author zhangguosheng
 */
@Service("sysConfigService")
public class SysConfigServiceImpl extends GenericService<SysConfig, ObjectId> implements SysConfigService{

	@Autowired
	private SysConfigDao dao;
	
	@Override
	public IGenericDao<SysConfig, ObjectId> getDao() {
		return dao;
	}
	
}
