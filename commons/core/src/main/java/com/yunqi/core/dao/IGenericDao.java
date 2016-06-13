package com.yunqi.core.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.mongodb.core.query.Query;

import com.yunqi.core.entity.BaseEntity;
import com.yunqi.core.entity.Page;  

public interface IGenericDao<T extends BaseEntity<T>, PK extends Serializable>{
	
    /** 
     * 保存一个对象到mongodb 
     * @param entity 
     * @return 
     */  
    public T save(T entity) ; 
    
    /**
     * 删除
     * @param pk
     */
    public void remove(PK pk);
    
    /** 
     * 通过ID获取记录 
     * @param id 
     * @return 
     */  
    public T findById(PK pk) ;  
    
    /**
     * 查询所有
     * @return
     */
    public List<T> findAll() ;
    
    public List<T> find(Query query) ;
    
    /** 
     * 分页查询 
     * @param page 
     * @param query 
     * @return 
     */  
    public Page<T> findPage(Page<T> page, Query query);  
      
    /** 
     * 求数据总和 
     * @param query 
     * @return 
     */  
    public long count(Query query);  
	
}
