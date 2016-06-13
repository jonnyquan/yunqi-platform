package com.yunqi.model;

import org.springframework.data.mongodb.core.mapping.Document;

import com.yunqi.core.entity.MongoEntity;

/**
 * 账号.
 */
@Document
public class SysConfig extends MongoEntity<SysConfig>{

	private static final long serialVersionUID = 8149945607408220407L;
	
	private String key;
	
	private String value;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
