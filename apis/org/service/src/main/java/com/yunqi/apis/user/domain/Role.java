package com.yunqi.apis.user.domain;

import java.util.Set;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.yunqi.apis.user.domain.enums.RoleType;
import com.yunqi.core.entity.MongoEntity;

@Document
public class Role extends MongoEntity<Role>{

	private static final long serialVersionUID = 5516054597378820764L;
	
	@DBRef
	private Set<Resource> resources;
	private String name;
	private RoleType type;
	private String remark;
	private String code;
	private String value;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public RoleType getType() {
		return type;
	}
	public void setType(RoleType type) {
		this.type = type;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Set<Resource> getResources() {
		return resources;
	}
	public void setResources(Set<Resource> resources) {
		this.resources = resources;
	}

}
