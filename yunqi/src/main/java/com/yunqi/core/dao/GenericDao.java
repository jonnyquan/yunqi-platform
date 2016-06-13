package com.yunqi.core.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.yunqi.core.entity.BaseEntity;
import com.yunqi.core.entity.Page;
import com.yunqi.core.id.IdGenerator;
import com.yunqi.core.util.ReflectionUtils;

public abstract class GenericDao<T extends BaseEntity<T>, PK extends Serializable> implements IGenericDao<T, PK> {
	
	private final static String ID_KEY = "id";

	@Autowired
	protected IdGenerator<Long> idGenerator;

	@Autowired
	protected MongoTemplate mongoTemplate;

	@Override
	public T save(T entity) {
//		if(entity.getId()==null){
//			entity.setId(idGenerator.generate());
//		}
		mongoTemplate.save(entity);
		return entity;
	}
	
	@Override
	public void remove(PK pk) {
		Criteria criteria = new Criteria(ID_KEY).is(pk);
		mongoTemplate.remove(new Query(criteria), this.getEntityClass());
	}

	@Override
	public T findById(PK pk) {
		return mongoTemplate.findById(pk, this.getEntityClass());
	}
	
	@Override
	public List<T> find(Query query) {
		return mongoTemplate.find(query, this.getEntityClass());
	}

	@Override
	public List<T> findAll() {
		return mongoTemplate.findAll(this.getEntityClass());
	}

	@Override
	public Page<T> findPage(Page<T> page, Query query) {
		long count = this.count(query);
		page.setTotalRecord(count);
		int pageNo = page.getPageNo();
		int pageSize = page.getPageSize();
		query.skip((pageNo - 1) * pageSize).limit(pageSize);
		List<T> rows = this.find(query);
		page.setResults(rows);
		return page;
	}

	@Override
	public long count(Query query) {
		return mongoTemplate.count(query, this.getEntityClass());
	}


	/**
	 * 获取需要操作的实体类class
	 * 
	 * @return
	 */
	private Class<T> getEntityClass() {
		return ReflectionUtils.getSuperClassGenricType(getClass());
	}

}
