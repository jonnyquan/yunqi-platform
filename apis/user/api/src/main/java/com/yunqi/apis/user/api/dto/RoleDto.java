package com.yunqi.apis.user.api.dto;

import java.io.Serializable;

public class RoleDto implements Serializable{

	private static final long serialVersionUID = -721043822684109087L;
	
//	private Set<ResourceDto> resources;
	private String name;
	private String type;
	private String remark;
	private String code;
	private String value;
	
//	public Set<ResourceDto> getResources() {
//		return resources;
//	}
//	public void setResources(Set<ResourceDto> resources) {
//		this.resources = resources;
//	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
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
	
}
