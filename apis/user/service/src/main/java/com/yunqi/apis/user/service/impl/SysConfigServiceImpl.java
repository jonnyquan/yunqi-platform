package com.yunqi.apis.user.service.impl;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunqi.apis.user.dao.SysConfigDao;
import com.yunqi.apis.user.model.SysConfig;
import com.yunqi.apis.user.service.SysConfigService;
import com.yunqi.core.dao.IGenericDao;
import com.yunqi.core.service.GenericService;

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
