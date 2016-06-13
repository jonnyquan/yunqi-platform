package com.yunqi.apis.user.domain.enums;

/**
 * 状态枚举
 * @author zhangguosheng
 */
public enum CompanyState{
	
	/**
	 * 启用
	 */
	ENABLE("启用"),
	/**
	 * 禁用
	 */
	DISABLE("禁用"),
	/**
	 * 删除
	 */
	DELETE("删除");
	
	private String text;

	private CompanyState(String text) {
		this.text = text;
	}
	
	public String getId() {
		return this.name();
	}

	public String getText() {
		return text;
	}
	
}
