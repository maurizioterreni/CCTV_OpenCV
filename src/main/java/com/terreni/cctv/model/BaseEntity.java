package com.terreni.cctv.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseEntity{
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true)
	private String uuid;

	protected BaseEntity() {
	}

	public BaseEntity(String uuid) {
		this.uuid = uuid;
	}

	public Long getId() {
		return id;
	}

	void setId(Long id) {
		this.id = id;
	}

	public String getUuid() {
		return uuid;
	}

	void setUuid(String uuid) {
		this.uuid = uuid;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof BaseEntity)) {
			return false;
		}
		if (uuid == null) {
			return false;
		}
		
		BaseEntity other = (BaseEntity) obj;
		return uuid.equals(other.getUuid());
	}
}

