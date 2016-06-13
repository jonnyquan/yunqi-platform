package com.yunqi.core.id;

import java.io.Serializable;

/**
 * 主键生成器
 * @author zhangguosheng
 */
public interface IdGenerator<ID extends Serializable> {

	public ID generate();
}
