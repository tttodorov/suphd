package com.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Specialities")
public class Speciality {

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

	@Column(name = "name")
	private String name;

	@Column(name = "dateStart")
	private Date dateStart;

	@Column(name = "dateEnd")
	private Date dateEnd;

	@Column(name = "faculty")
	private Integer faculty;

	@Column(name = "isAcredited")
	private Boolean isAcredited;

	@Column(name = "code")
	private String code;

	@Column(name = "mesSpecialityId")
	private Integer mesSpecialityId;

	@Column(name = "mesProfGroup")
	private Integer mesProfGroup;

	@Column(name = "mesEdForm")
	private Integer mesEdForm;

	@Override
	public int hashCode() {
		return getId();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Speciality) {
			Speciality userPhdReport = (Speciality) obj;
			return userPhdReport.getId() == getId();
		}
		return false;
	}

	@Override
	public String toString() {
		return String
				.format("id: %d; isActive: %d; createOn: %s; createBy: %d; "
						+ "createOn: %s; createBy: %d; name: %s; faculty: %d; "
						+ "code: %s; isAcredited: %d; mesSpecialityId: %d; mesProfGroup: %d; mesEdForm: %d;",
						getId(), getIsActive(), getCreateOn(), getCreateBy(),
						getEditedOn(), getEditedOn(), getName(), getFaculty(),
						getCode(), getIsAcredited(), getMesSpecialityId(),
						getMesProfGroup(), getMesEdForm());
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDateStart() {
		return dateStart;
	}

	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}

	public Date getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}

	public Integer getFaculty() {
		return faculty;
	}

	public void setFaculty(Integer faculty) {
		this.faculty = faculty;
	}

	public Boolean getIsAcredited() {
		return isAcredited;
	}

	public void setIsAcredited(Boolean isAcredited) {
		this.isAcredited = isAcredited;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getMesSpecialityId() {
		return mesSpecialityId;
	}

	public void setMesSpecialityId(Integer mesSpecialityId) {
		this.mesSpecialityId = mesSpecialityId;
	}

	public Integer getMesProfGroup() {
		return mesProfGroup;
	}

	public void setMesProfGroup(Integer mesProfGroup) {
		this.mesProfGroup = mesProfGroup;
	}

	public Integer getMesEdForm() {
		return mesEdForm;
	}

	public void setMesEdForm(Integer mesEdForm) {
		this.mesEdForm = mesEdForm;
	}
}