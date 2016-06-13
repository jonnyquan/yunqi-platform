package com.yunqi.model;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.yunqi.core.entity.MongoEntity;
import com.yunqi.model.enums.CompanyState;

/**
 * 账号.
 */
@Document
public class Company extends MongoEntity<Company>{

	private static final long serialVersionUID = -4767403166004854200L;

	private String name;
	private Date createTime;
	@DBRef
	private Employee createEmp;
	private CompanyState state;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Employee getCreateEmp() {
		return createEmp;
	}
	public void setCreateEmp(Employee createEmp) {
		this.createEmp = createEmp;
	}
	public CompanyState getState() {
		return state;
	}
	public void setState(CompanyState state) {
		this.state = state;
	}
	
}
