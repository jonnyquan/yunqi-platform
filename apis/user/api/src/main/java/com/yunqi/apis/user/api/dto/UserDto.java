package com.yunqi.apis.user.api.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * 系统用户.
 */
public class UserDto implements Serializable{

	private static final long serialVersionUID = -8033365317984838645L;

	private Set<RoleDto> roles;

	private Set<ResourceDto> resource;
	
	private String name;
	
	private String email;
	
	private String tel;
	
	private Date lastLoginTime;
	
	private String lastLoginIp;

	private String state;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getLastLoginIp() {
		return lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Set<RoleDto> getRoles() {
		return roles;
	}

	public void setRoles(Set<RoleDto> roles) {
		this.roles = roles;
	}

	public Set<ResourceDto> getResource() {
		return resource;
	}

	public void setResource(Set<ResourceDto> resource) {
		this.resource = resource;
	}

}
