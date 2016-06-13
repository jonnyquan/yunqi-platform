package com.yunqi.model;

import org.springframework.data.mongodb.core.mapping.Document;

import com.yunqi.core.entity.MongoEntity;
import com.yunqi.model.enums.ResourceType;

@Document
public class Resource extends MongoEntity<Resource>{

	private static final long serialVersionUID = 9021763870913204543L;

    private String name;
    private ResourceType type;
    private String permission;
    private String code;
    private String remark;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ResourceType getType() {
		return type;
	}
	public void setType(ResourceType type) {
		this.type = type;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getPermission() {
		return permission;
	}
	public void setPermission(String permission) {
		this.permission = permission;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

}