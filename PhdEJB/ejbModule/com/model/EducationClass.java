package com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BasicClass")
public class EducationClass {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "isActive")
	private Integer isActive;

	@Column(name = "bClassId")
	private Integer mesId;

	@Column(name = "BclassName")
	private String abbreviation;

	@Column(name = "BclassRecode")
	private String mesName;

	@Column(name = "Details")
	private String details;

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
				"id: %d; isActive: %d; mesId: %d; mesName: %s; details: %s;",
				getId(), getIsActive(), getMesId(), getMesName(), getDetails());
	}

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

	public void setMesId(Integer mesId) {
		this.mesId = mesId;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public String getMesName() {
		return mesName;
	}

	public void setMesName(String mesMame) {
		this.mesName = mesMame;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}
}