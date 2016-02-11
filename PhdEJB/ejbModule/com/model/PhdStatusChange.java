package com.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PhdStatusChanges")
public class PhdStatusChange {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private Boolean isActive;
	private Date createOn;
	private Integer createBy;
	private Integer userId;
	private Integer statusOld;
	private Integer statusNew;
	private String notes;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
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

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer user) {
		this.userId = user;
	}

	public Integer getStatusOld() {
		return statusOld;
	}

	public void setStatusOld(Integer statusOld) {
		this.statusOld = statusOld;
	}

	public Integer getStatusNew() {
		return statusNew;
	}

	public void setStatusNew(Integer statusNew) {
		this.statusNew = statusNew;
	}

	@Override
	public int hashCode() {
		return getId();
	}

	@Override
	public boolean equals(Object obj) {

		if (obj instanceof PhdStatusChange) {
			PhdStatusChange userPhdReport = (PhdStatusChange) obj;
			return userPhdReport.getId() == getId();
		}

		return false;
	}

	@Override
	public String toString() {
		return String
				.format("id: %d; isActive: %d; createOn: %s; createBy: %s; userId: %d; statusOld: %d; statusNew: %d; notes: %s;",
						getId(), getIsActive(), getCreateOn(), getCreateBy(),
						getUserId(), getStatusOld(), getStatusNew(), getNotes());
	}

	public String getNotes() {
		if(notes == null) {
			return "";
		}
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
}