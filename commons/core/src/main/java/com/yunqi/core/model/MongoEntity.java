package com.yunqi.core.model;

import java.io.Serializable;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class MongoEntity<T extends MongoEntity<T>> extends BaseEntity<T>{

	private static final long serialVersionUID = 4275566650516425892L;

	@Id
	protected ObjectId id;

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
		MongoEntity<?> other = (MongoEntity<?>) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public ObjectId  getId() {
		return id;
	}

	public void setId(ObjectId  id) {
		this.id = id;
	}

	@Override
	public void setId(Serializable id) {
		this.id = (ObjectId )id;
	}

}
