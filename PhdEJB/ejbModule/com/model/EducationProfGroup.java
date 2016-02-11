package com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CodeProfGroup")
public class EducationProfGroup {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "isActive")
	private Integer isActive;

	@Column(name = "ProfGroupID")
	private Integer mesId;

	@Column(name = "ProfGroupName")
	private String mesName;

	@Column(name = "ProfAreaName")
	private String profAreaName;

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
				"id: %d; isActive: %d; mesId: %d; mesName: %s;",
				getId(), getIsActive(), getMesId(), getMesName());
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

	public String getMesName() {
		return mesName;
	}

	public void setMesName(String mesMame) {
		this.mesName = mesMame;
	}

	public String getProfAreaName() {
		return profAreaName;
	}

	public void setProfAreaName(String profAreaName) {
		this.profAreaName = profAreaName;
	}

}