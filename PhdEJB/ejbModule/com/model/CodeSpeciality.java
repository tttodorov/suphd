package com.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CodeSpeciality")
public class CodeSpeciality {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "isActive")
	private Integer isActive;
	
	@Column(name = "createOn")
	private Date createOn;

	@Column(name = "createBy")
	private Integer createBy;

	@Column(name = "editedOn")
	private Date editedOn;

	@Column(name = "editedBy")
	private Integer editedBy;

	@Column(name = "SpecialityID")
	private Integer mesId;
	
	@Column(name = "SpecialityName")
	private String mesName;

	@Column(name = "ProfAreaName")
	private String profAreaName;

	@Column(name = "ProfGroupName")
	private String profGroupName;

	@Column(name = "SE_TypeName")
	private String seTypeName;

	@Column(name = "MasterTypeName")
	private String masterTypeName;
	
	@Override
	public int hashCode() {
		return getId();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof CodeSpeciality) {
			CodeSpeciality userPhdReport = (CodeSpeciality) obj;
			return userPhdReport.getId() == getId();
		}
		return false;
	}

	@Override
	public String toString() {
		return String.format("Id: %d; IsActive: %d; mesId: %d; mesName: %s;",
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

	public Date getCreateOn() {
		return createOn;
	}

	public void setCreateOn(Date createOn) {
		this.createOn = createOn;
	}

	public Integer getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Integer createBy) {
		this.createBy = createBy;
	}

	public Date getEditedOn() {
		return editedOn;
	}

	public void setEditedOn(Date editedOn) {
		this.editedOn = editedOn;
	}

	public Integer getEditedBy() {
		return editedBy;
	}

	public void setEditedBy(Integer editedBy) {
		this.editedBy = editedBy;
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

	public void setMesName(String mesName) {
		this.mesName = mesName;
	}

	public String getProfAreaName() {
		return profAreaName;
	}

	public void setProfAreaName(String profAreaName) {
		this.profAreaName = profAreaName;
	}

	public String getProfGroupName() {
		return profGroupName;
	}

	public void setProfGroupName(String profGroupName) {
		this.profGroupName = profGroupName;
	}

	public String getSeTypeName() {
		return seTypeName;
	}

	public void setSeTypeName(String seTypeName) {
		this.seTypeName = seTypeName;
	}

	public String getMasterTypeName() {
		return masterTypeName;
	}

	public void setMasterTypeName(String masterTypeName) {
		this.masterTypeName = masterTypeName;
	}
}