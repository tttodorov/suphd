package com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CodeEdDuration")
public class EducationDuration {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "isActive")
	private Integer isActive;

	@Column(name = "EdDurationID")
	private Integer mesId;
	
	@Column(name = "EdDurationName")
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

	@Override
	public int hashCode() {
		return getId();
	}

	@Override
	public boolean equals(Object obj) {

		if (obj instanceof EducationForm) {
			EducationForm userPhdReport = (EducationForm) obj;
			return userPhdReport.getId() == getId();
		}

		return false;
	}

	@Override
	public String toString() {
		return String.format(
				"Id: %d; IsActive: %d; EdDurationId: %d; EdDurationId: %s;", getId(),
				getIsActive(), getMesId(), getMesId());
	}

	public Integer getMesId() {
		return mesId;
	}

	public void setMesId(Integer edFormId) {
		this.mesId = edFormId;
	}

	public String getMesName() {
		return mesName;
	}

	public void setMesName(String edFormName) {
		this.mesName = edFormName;
	}
}