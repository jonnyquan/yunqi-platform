package com.yunqi.core.service;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.util.Assert;

import com.yunqi.core.dao.IGenericDao;
import com.yunqi.core.id.IdGenerator;
import com.yunqi.core.model.BaseEntity;
import com.yunqi.core.model.Page;

/**
 * @author zhangguosheng
 */
public abstract class GenericService<T extends BaseEntity<T>, PK extends Serializable> implements IGenericService<T, PK>{
	
	public final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource(name="idGenerator")
	protected IdGenerator<Long> idGenerator;

	public abstract IGenericDao<T,PK> getDao();
	
	@Override
	public void save(T entity) {
		Assert.notNull(entity);
		getDao().save(entity);
	}
	
	@Override
	public void delete(PK id) {
		Assert.notNull(id);
		getDao().remove(id);
	}
	
	@Override
	public T findById(PK id) {
		Assert.notNull(id);
		return getDao().findById(id);
	}

	@Override
	public List<T> findAll() {
		return getDao().findAll();
	}

	@Override
	public Page<T> pageAll(Page<T> page){
		Assert.notNull(page);
		return getDao().findPage(page, new Query());
	}

}
