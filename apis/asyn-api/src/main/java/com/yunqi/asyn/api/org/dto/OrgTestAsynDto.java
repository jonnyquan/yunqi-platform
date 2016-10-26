package com.yunqi.asyn.api.org.dto;

import java.io.Serializable;

public class OrgTestAsynDto implements Serializable{

	private static final long serialVersionUID = 1127354900762035830L;
	
	private Long id;
	private boolean isRead;
	private String name;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public boolean isRead() {
		return isRead;
	}
	public void setRead(boolean isRead) {
		this.isRead = isRead;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
