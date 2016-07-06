package com.yunqi.apis.user.dao.impl;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import com.yunqi.apis.user.dao.SysConfigDao;
import com.yunqi.apis.user.domain.SysConfig;
import com.yunqi.core.dao.GenericDao;

@Service("sysConfigDao")
public class SysConfigDaoImpl extends GenericDao<SysConfig, ObjectId> implements SysConfigDao{

}
