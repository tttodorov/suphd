package com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CodeEdForm")
public class EducationForm {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "isActive")
	private Integer isActive;

	@Column(name = "EdFormID")
	private Integer mesId;
	
	@Column(name = "EdFormName")
	private String mesName;
	
	private String edFormSN;
	
	private String details;

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
				"Id: %d; IsActive: %d; EdFormId: %d; EdFormName: %s;", getId(),
				getIsActive(), getMesId(), getMesName());
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

	public String getEdFormSN() {
		return edFormSN;
	}

	public void setEdFormSN(String edFormSN) {
		this.edFormSN = edFormSN;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}
}