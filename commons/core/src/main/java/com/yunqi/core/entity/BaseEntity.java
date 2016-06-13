package com.yunqi.core.entity;

import java.io.Serializable;

public abstract class BaseEntity<T> implements Serializable {

	private static final long serialVersionUID = 9071309727314398599L;
	
	public abstract Serializable getId();
	
	public abstract void setId(Serializable id);

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		return true;
	}
	
}
