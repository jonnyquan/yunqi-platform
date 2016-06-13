package com.yunqi.dao.impl;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import com.yunqi.core.dao.GenericDao;
import com.yunqi.dao.SysConfigDao;
import com.yunqi.model.SysConfig;

@Service("sysConfigDao")
public class SysConfigDaoImpl extends GenericDao<SysConfig, ObjectId> implements SysConfigDao{

}
