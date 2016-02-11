package com.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CodeSpecialityAdminPro")
public class CodeSpecialityAdminUni {

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
	private Integer specialityId;
	
	@Column(name = "SpecialityType")
	private Integer specialityType;
	
	@Column(name = "SpecialityName")
	private String specialityName;

	@Column(name = "SpecialityProfArea")
	private String specialityProfArea;

	@Column(name = "SpecialityProfGroup")
	private String specialityProfGroup;
	
	@Override
	public int hashCode() {
		return getId();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof CodeSpecialityAdminUni) {
			CodeSpecialityAdminUni userPhdReport = (CodeSpecialityAdminUni) obj;
			return userPhdReport.getId() == getId();
		}
		return false;
	}

	@Override
	public String toString() {
		return String.format("id: %d; isActive: %d; specialityId: %d; specialityName: %s",
				getId(), getIsActive(), getSpecialityId(), getSpecialityName());
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

	public Integer getSpecialityId() {
		return specialityId;
	}

	public void setSpecialityId(Integer specialityId) {
		this.specialityId = specialityId;
	}

	public Integer getSpecialityType() {
		return specialityType;
	}

	public void setSpecialityType(Integer specialityType) {
		this.specialityType = specialityType;
	}

	public String getSpecialityName() {
		return specialityName;
	}

	public void setSpecialityName(String specialityName) {
		this.specialityName = specialityName;
	}

	public String getSpecialityProfArea() {
		return specialityProfArea;
	}

	public void setSpecialityProfArea(String specialityProfArea) {
		this.specialityProfArea = specialityProfArea;
	}

	public String getSpecialityProfGroup() {
		return specialityProfGroup;
	}

	public void setSpecialityProfGroup(String specialityProfGroup) {
		this.specialityProfGroup = specialityProfGroup;
	}

}