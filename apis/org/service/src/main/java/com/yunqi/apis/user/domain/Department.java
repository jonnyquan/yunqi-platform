package com.yunqi.apis.user.domain;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.yunqi.apis.user.domain.enums.DepartmentState;
import com.yunqi.core.entity.MongoEntity;

/**
 * 账号.
 */
@Document
public class Department extends MongoEntity<Department>{

	private static final long serialVersionUID = -666441768296279247L;
	
	private String name;
	@DBRef
	private Department parentDep;
	private Company company;
	private Date createTime;
	@DBRef
	private Employee createEmp;
	private DepartmentState state;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Department getParentDep() {
		return parentDep;
	}
	public void setParentDep(Department parentDep) {
		this.parentDep = parentDep;
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
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
	public DepartmentState getState() {
		return state;
	}
	public void setState(DepartmentState state) {
		this.state = state;
	}
	
}
