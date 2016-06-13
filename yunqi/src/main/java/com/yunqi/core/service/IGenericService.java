package com.yunqi.core.service;

import java.io.Serializable;
import java.util.List;

import com.yunqi.core.entity.BaseEntity;
import com.yunqi.core.entity.Page;

/**
 * @author zhangguosheng
 */
public interface IGenericService<T extends BaseEntity<T>, PK extends Serializable>{
	
	public void save(T entity);
	
	public void delete(PK id);
	
	public T findById(PK id);
	
	public List<T> findAll();
	
	public Page<T> pageAll(Page<T> page);
	
}
