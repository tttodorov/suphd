package com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CodeUniversity")
public class University {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "isActive")
	private Integer isActive;
	
	@Column(name = "UniversityID")
	private Integer mesId;
	
	@Column(name = "UniversityName")
	private String mesName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIsActive() {
		return isActive;
	}

	public void setIsActive(Integer isActive) {
		if (isActive == null) {
			isActive = 1;
		}
		this.isActive = isActive;
	}

	public Integer getMesId() {
		return mesId;
	}

	public void setMesId(Integer universityId) {
		this.mesId = universityId;
	}

	public String getMesName() {
		return mesName;
	}

	public void setMesName(String universityName) {
		this.mesName = universityName;
	}

	@Override
	public int hashCode() {
		return getId();
	}

	@Override
	public boolean equals(Object obj) {

		if (obj instanceof University) {
			University userPhdReport = (University) obj;
			return userPhdReport.getId() == getId();
		}

		return false;
	}

	@Override
	public String toString() {
		return String.format(
				"Id: %d; IsActive: %d; UniversityId: %d; UniversityName: %s;",
				getId(), getIsActive(), getMesId(), getMesName());
	}
}