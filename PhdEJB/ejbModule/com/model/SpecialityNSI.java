package com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CodeNSI")
public class SpecialityNSI {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "isActive")
	private Integer isActive;

	@Column(name = "NSIID")
	private Integer mesId;

	@Column(name = "NSIName")
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

		if (obj instanceof SpecialityNSI) {
			SpecialityNSI userPhdReport = (SpecialityNSI) obj;
			return userPhdReport.getId() == getId();
		}

		return false;
	}

	@Override
	public String toString() {
		return String.format("Id: %d; IsActive: %d; mesId: %d; mesName: %s;",
				getId(), getIsActive(), getMesId(), getMesName());
	}

	public Integer getMesId() {
		return mesId;
	}

	public void setMesId(Integer nSIId) {
		mesId = nSIId;
	}

	public String getMesName() {
		return mesName;
	}

	public void setMesName(String nSIName) {
		mesName = nSIName;
	}
}