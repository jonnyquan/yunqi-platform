package com.yunqi.apis.user.model.enums;

/**
 * 组类型
 * @author zhangguosheng
 */
public enum RoleType{

	ADMIN("管理员"),

	TEST("测试");
	
	private String text;

	private RoleType(String text) {
		this.text = text;
	}
	
	public String getId() {
		return this.name();
	}

	public String getText() {
		return text;
	}
	
}
