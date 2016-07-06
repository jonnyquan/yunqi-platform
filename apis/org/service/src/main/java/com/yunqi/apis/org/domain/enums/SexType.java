package com.yunqi.apis.org.domain.enums;

/**
 * 组类型
 * @author zhangguosheng
 */
public enum SexType{

	MALE("男"),

	FEMALE("女"),
	
	UNKNOW("未知");
	
	private String text;

	private SexType(String text) {
		this.text = text;
	}
	
	public String getId() {
		return this.name();
	}

	public String getText() {
		return text;
	}
	
}
