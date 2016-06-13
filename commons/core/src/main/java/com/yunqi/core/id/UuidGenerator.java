package com.yunqi.core.id;

import java.util.UUID;

/**
 * uuid型的ID生成器
 * @author zhangguosheng
 */
public class UuidGenerator implements IdGenerator<String>{

	@Override
	public String generate() {
		return UUID.randomUUID().toString();
	}

}
