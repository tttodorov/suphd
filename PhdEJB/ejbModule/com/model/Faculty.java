package com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Faculties")
public class Faculty {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "isActive")
	private Boolean isActive;

	@Column(name = "createBy")
	private Integer createBy;

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

	@Column(name = "mesId")
	private Integer mesId;

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
		if (obj instanceof Faculty) {
			Faculty group = (Faculty) obj;
			return group.getId() == getId();
		}
		return false;
	}

	@Override
	public String toString() {
		return String
				.format("id: %d; isActive: %b; createBy: %d; editedBy: %d; parent: %d; name: %s; abbreviation: %s; susiId: %d; mesId: %d; staff: %b; student: %b; local: %b;",
						getId(), getIsActive(), getCreateBy(), getEditedBy(),
						getParent(), getName(), getAbbreviation(), getSusiId(),
						getMesId(), getStaff(), getStudent(), getLocal());
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

	public Integer getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Integer createBy) {
		this.createBy = createBy;
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

	public Integer getMesId() {
		return mesId;
	}

	public void setMesId(Integer mesId) {
		this.mesId = mesId;
	}
}