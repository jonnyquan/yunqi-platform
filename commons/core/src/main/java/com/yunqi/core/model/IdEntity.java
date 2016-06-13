package com.yunqi.core.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;

public class IdEntity<T extends IdEntity<T>> extends BaseEntity<T>{

	private static final long serialVersionUID = 4275566650516425892L;

	@Id
	protected Long id;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		IdEntity<?> other = (IdEntity<?>) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public void setId(Serializable id) {
		this.id = (Long)id;
	}

}
