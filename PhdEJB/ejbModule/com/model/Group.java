package com.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Groups")
public class Group {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "isActive")
	private Boolean isActive;

	@Column(name = "createOn")
	private Date createOn;

	@Column(name = "createBy")
	private Integer createBy;

	@Column(name = "editedOn")
	private Date editedOn;

	@Column(name = "editedBy")
	private Integer editedBy;

	@Column(name = "parent")
	private Integer parent;

	@Column(name = "name")
	private String name;

	@Column(name = "abbreviation")
	private String abbreviation;

	@Column(name = "susiId")
	private Integer susiId;

	@Column(name = "local")
	private Boolean local;

	@Column(name = "staff")
	private Boolean staff;

	@Column(name = "student")
	private Boolean student;

	@Override
	public int hashCode() {
		return getId();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Group) {
			Group group = (Group) obj;
			return group.getId() == getId();
		}
		return false;
	}

	@Override
	public String toString() {
		return String
				.format("id: %d; isActive: %b; createOn: %s; createBy: %d; editedOn: %s; editedBy: %d; parent: %d; name: %s; abbreviation: %s; susiId: %d; staff: %b; student: %b; local: %b;",
						getId(), getIsActive(), getCreateOn(), getCreateBy(),
						getEditedOn(), getEditedBy(), getParent(), getName(),
						getAbbreviation(), getSusiId(), getStaff(),
						getStudent(), getLocal());
	}

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
		if (isActive == null) {
			isActive = true;
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

	public Integer getParent() {
		return parent;
	}

	public void setParent(Integer parent) {
		this.parent = parent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public Integer getSusiId() {
		return susiId;
	}

	public void setSusiId(Integer susiId) {
		this.susiId = susiId;
	}

	public Boolean getLocal() {
		return local;
	}

	public void setLocal(Boolean local) {
		this.local = local;
	}

	public Boolean getStaff() {
		return staff;
	}

	public void setStaff(Boolean staff) {
		this.staff = staff;
	}

	public Boolean getStudent() {
		return student;
	}

	public void setStudent(Boolean student) {
		this.student = student;
	}
}