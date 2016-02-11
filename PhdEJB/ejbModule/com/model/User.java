package com.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Users")
public class User {

	// unique identifier
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	// mark whether record in db is in use
	@Column(name = "isActive")
	private Boolean isActive;

	// when record is created in db
	@Column(name = "createOn")
	private Date createOn;

	// who created the record in db
	@Column(name = "createBy")
	private Integer createBy;

	// when record is last updated in db
	@Column(name = "editedOn")
	private Date editedOn;

	// whose is the last record updated in db
	@Column(name = "editedBy")
	private Integer editedBy;

	// whether userPhd is allowed to self edit
	private Boolean academicsBanned;

	// System profile
	// system username
	private String profileUsername;
	// system password
	private String profilePassword;
	// system email
	private String profileEmail;
	// system role
	private String profileRole;
	// system profile photo
	private String profilePhoto;
	// system profile title in institution
	private String profileTitle;
	// system array of profile groups included
	private String profileGroups;
	// system array of groups able to access
	private String profileGroupsAccess;

	// System roles
	private Boolean rolePhd;
	private Boolean roleAdmin;
	private Boolean roleInspectorFaculty;
	private Boolean roleAdminUniversity;
	private Boolean roleInspectorScholarships;
	private Boolean roleInspectorHostel;
	private Boolean roleSupervisor;

	// Personal Data
	private String personalNameFirst;
	private String personalNameFirstLatin;
	private String personalNameSecond;
	private String personalNameSecondLatin;
	private String personalNameThird;
	private String personalNameThirdLatin;
	private String personalNameOther;
	private String personalNameOtherLatin;
	private Integer personalGender;
	private Integer personalBirthCountry;
	private Integer personalBirthCity;
	private Date personalBirthDate;

	// Citizenship
	private Integer personalIdNumberType;
	private String personalIdNumber;
	private Integer personalIdNumberCountry;
	private Integer personalCitizenship1;
	private String personalCitizenship1IdNumber;
	private Integer personalCitizenship2;
	private String personalCitizenship2IdNumber;

	// Contacts
	private Integer contactCountry;
	private Integer contactCity;
	private String contactStreet;
	private String contactStreetNumber;
	private String contactFloor;
	private String contactApartment;
	private String contactPhone;
	private String contactPhone2;
	private String contactFacebook;
	private String contactTwitter;
	private String contactSkype;

	// Education
	private Integer masterCountry;
	private Integer masterCity;
	private Integer masterUniversity;
	private String masterUniversityName;
	private Integer masterSpeciality;
	private String masterProfession;
	private Integer masterProfGroup;
	private String masterDiplomaNumber;
	private Date masterDiplomaDate;

	// Academic Data
	private String academicsSpecialityTheme;
	private Integer academicsDuration;
	private Integer academicsStatus;
	private Date academicsStatusDate;
	private String academicsStatusNotes;
	private Integer academicsForm;
	private Integer academicsPayment;
	private Integer academicsReasonsForAcc;
	private Integer academicsChangeUniversity;
	private Date academicsYearOfAdmission;
	private Date academicsCalendarYearOfAdmission;
	private Date academicsYearOfGraduation;
	private Boolean academicsScholarship;
	private Boolean academicsHostel;
	private Boolean academicsHolidays;
	private Boolean academicsInternationalProgram;
	private Date academicsInternationalProgramStart;
	private Date academicsInternationalProgramEnd;
	private Integer academicsFaculty;
	private Integer academicsDepartment;
	private String academicsFacultyNumber;
	private Integer academicsSpeciality;
	private Integer academicsSpecialityNSI;
	private String academicsSpecialitySubjectCode;
	private Integer academicsProfGroup;
	private Integer academicsCourse;
	private String academicsWorkingLanguage;
	private Boolean academicsDegreeWithAnotherUniversity;
	private Boolean academicsNewSpecialty;
	private Boolean academicsReadyForReportMes;

	@Override
	public int hashCode() {
		return getId();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof User) {
			User user = (User) obj;
			return user.getProfileUsername().equals(getProfileUsername());
		}
		return false;
	}

	@Override
	public String toString() {
		return String.format("Username: %s; Email: %s; NameFirst: %s; "
				+ "Admin: %s;	InspectorFaculty: %s; AdminUniversity: %s;"
				+ " InspectorHostel: %s; InspectorScholarships: %s;"
				+ " Supervisor: %s; Phd: %s", getProfileUsername(),
				getProfileEmail(), getPersonalNameFirst(), getRoleAdmin(),
				getRoleInspectorFaculty(), getRoleAdminUniversity(),
				getRoleInspectorHostel(), getRoleInspectorScholarships(),
				getRoleSupervisor(), getRolePhd());
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getIsActive() {
		if(isActive == null) {
			return false;
		}
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		if(isActive == null) {
			isActive = false;
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

	public Boolean getAcademicsBanned() {
		return academicsBanned;
	}

	public void setAcademicsBanned(Boolean academicsBanned) {
		if (academicsBanned == null) {
			academicsBanned = false;
		}
		this.academicsBanned = academicsBanned;
	}

	public String getProfileUsername() {
		return profileUsername;
	}

	public void setProfileUsername(String profileUsername) {
		this.profileUsername = profileUsername;
	}

	public String getProfilePassword() {
		return profilePassword;
	}

	public void setProfilePassword(String profilePassword) {
		this.profilePassword = profilePassword;
	}

	public String getProfileEmail() {
		return profileEmail;
	}

	public void setProfileEmail(String profileEmail) {
		this.profileEmail = profileEmail;
	}

	public String getProfileRole() {
		return profileRole;
	}

	public void setProfileRole(String profileRole) {
		this.profileRole = profileRole;
	}

	public String getProfilePhoto() {
		return profilePhoto;
	}

	public void setProfilePhoto(String profilePhoto) {
		this.profilePhoto = profilePhoto;
	}

	public String getProfileTitle() {
		return profileTitle;
	}

	public void setProfileTitle(String profileTitle) {
		this.profileTitle = profileTitle;
	}

	public String getProfileGroups() {
		return profileGroups;
	}

	public void setProfileGroups(String profileGroups) {
		this.profileGroups = profileGroups;
	}

	public String getProfileGroupsAccess() {
		return profileGroupsAccess;
	}

	public void setProfileGroupsAccess(String profileGroupsAccess) {
		this.profileGroupsAccess = profileGroupsAccess;
	}

	public Boolean getRolePhd() {
		return rolePhd;
	}

	public void setRolePhd(Boolean rolePhd) {
		if (rolePhd == null) {
			rolePhd = false;
		}
		this.rolePhd = rolePhd;
	}

	public Boolean getRoleAdmin() {
		return roleAdmin;
	}

	public void setRoleAdmin(Boolean roleAdmin) {
		if (roleAdmin == null) {
			roleAdmin = false;
		}
		this.roleAdmin = roleAdmin;
	}

	public Boolean getRoleInspectorFaculty() {
		return roleInspectorFaculty;
	}

	public void setRoleInspectorFaculty(Boolean roleInspectorFaculty) {
		if (roleInspectorFaculty == null) {
			roleInspectorFaculty = false;
		}
		this.roleInspectorFaculty = roleInspectorFaculty;
	}

	public Boolean getRoleAdminUniversity() {
		return roleAdminUniversity;
	}

	public void setRoleAdminUniversity(Boolean roleAdminUniversity) {
		if (roleAdminUniversity == null) {
			roleAdminUniversity = false;
		}
		this.roleAdminUniversity = roleAdminUniversity;
	}

	public Boolean getRoleInspectorScholarships() {
		return roleInspectorScholarships;
	}

	public void setRoleInspectorScholarships(Boolean roleInspectorScholarships) {
		if (roleInspectorScholarships == null) {
			roleInspectorScholarships = false;
		}
		this.roleInspectorScholarships = roleInspectorScholarships;
	}

	public Boolean getRoleInspectorHostel() {
		return roleInspectorHostel;
	}

	public void setRoleInspectorHostel(Boolean roleInspectorHostel) {
		if (roleInspectorHostel == null) {
			roleInspectorHostel = false;
		}
		this.roleInspectorHostel = roleInspectorHostel;
	}

	public Boolean getRoleSupervisor() {
		return roleSupervisor;
	}

	public void setRoleSupervisor(Boolean roleSupervisor) {
		if (roleSupervisor == null) {
			roleSupervisor = false;
		}
		this.roleSupervisor = roleSupervisor;
	}

	public String getPersonalNameFirst() {
		return personalNameFirst;
	}

	public void setPersonalNameFirst(String personalNameFirst) {
		this.personalNameFirst = personalNameFirst;
	}

	public String getPersonalNameFirstLatin() {
		return personalNameFirstLatin;
	}

	public void setPersonalNameFirstLatin(String personalNameFirstLatin) {
		this.personalNameFirstLatin = personalNameFirstLatin;
	}

	public String getPersonalNameSecond() {
		return personalNameSecond;
	}

	public void setPersonalNameSecond(String personalNameSecond) {
		this.personalNameSecond = personalNameSecond;
	}

	public String getPersonalNameSecondLatin() {
		return personalNameSecondLatin;
	}

	public void setPersonalNameSecondLatin(String personalNameSecondLatin) {
		this.personalNameSecondLatin = personalNameSecondLatin;
	}

	public String getPersonalNameThird() {
		return personalNameThird;
	}

	public void setPersonalNameThird(String personalNameThird) {
		this.personalNameThird = personalNameThird;
	}

	public String getPersonalNameThirdLatin() {
		return personalNameThirdLatin;
	}

	public void setPersonalNameThirdLatin(String personalNameThirdLatin) {
		this.personalNameThirdLatin = personalNameThirdLatin;
	}

	public String getPersonalNameOther() {
		return personalNameOther;
	}

	public void setPersonalNameOther(String personalNameOther) {
		this.personalNameOther = personalNameOther;
	}

	public String getPersonalNameOtherLatin() {
		return personalNameOtherLatin;
	}

	public void setPersonalNameOtherLatin(String personalNameOtherLatin) {
		this.personalNameOtherLatin = personalNameOtherLatin;
	}

	public Integer getPersonalGender() {
		return personalGender;
	}

	public void setPersonalGender(Integer personalGender) {
		this.personalGender = personalGender;
	}

	public Integer getPersonalBirthCountry() {
		return personalBirthCountry;
	}

	public void setPersonalBirthCountry(Integer personalBirthCountry) {
		this.personalBirthCountry = personalBirthCountry;
	}

	public Integer getPersonalBirthCity() {
		return personalBirthCity;
	}

	public void setPersonalBirthCity(Integer personalBirthCity) {
		this.personalBirthCity = personalBirthCity;
	}

	public Date getPersonalBirthDate() {
		return personalBirthDate;
	}

	public void setPersonalBirthDate(Date personalBirthDate) {
		this.personalBirthDate = personalBirthDate;
	}

	public Integer getPersonalIdNumberType() {
		return personalIdNumberType;
	}

	public void setPersonalIdNumberType(Integer personalIdNumberType) {
		this.personalIdNumberType = personalIdNumberType;
	}

	public String getPersonalIdNumber() {
		return personalIdNumber;
	}

	public void setPersonalIdNumber(String personalIdNumber) {
		this.personalIdNumber = personalIdNumber;
	}

	public Integer getPersonalIdNumberCountry() {
		return personalIdNumberCountry;
	}

	public void setPersonalIdNumberCountry(Integer personalIdNumberCountry) {
		this.personalIdNumberCountry = personalIdNumberCountry;
	}

	public Integer getPersonalCitizenship1() {
		return personalCitizenship1;
	}

	public void setPersonalCitizenship1(Integer personalCitizenship1) {
		this.personalCitizenship1 = personalCitizenship1;
	}

	public String getPersonalCitizenship1IdNumber() {
		return personalCitizenship1IdNumber;
	}

	public void setPersonalCitizenship1IdNumber(
			String personalCitizenship1IdNumber) {
		this.personalCitizenship1IdNumber = personalCitizenship1IdNumber;
	}

	public Integer getPersonalCitizenship2() {
		return personalCitizenship2;
	}

	public void setPersonalCitizenship2(Integer personalCitizenship2) {
		this.personalCitizenship2 = personalCitizenship2;
	}

	public String getPersonalCitizenship2IdNumber() {
		return personalCitizenship2IdNumber;
	}

	public void setPersonalCitizenship2IdNumber(
			String personalCitizenship2IdNumber) {
		this.personalCitizenship2IdNumber = personalCitizenship2IdNumber;
	}

	public Integer getContactCountry() {
		return contactCountry;
	}

	public void setContactCountry(Integer contactCountry) {
		this.contactCountry = contactCountry;
	}

	public Integer getContactCity() {
		return contactCity;
	}

	public void setContactCity(Integer contactCity) {
		this.contactCity = contactCity;
	}

	public String getContactStreet() {
		return contactStreet;
	}

	public void setContactStreet(String contactStreet) {
		this.contactStreet = contactStreet;
	}

	public String getContactStreetNumber() {
		return contactStreetNumber;
	}

	public void setContactStreetNumber(String contactStreetNumber) {
		this.contactStreetNumber = contactStreetNumber;
	}

	public String getContactFloor() {
		return contactFloor;
	}

	public void setContactFloor(String contactFloor) {
		this.contactFloor = contactFloor;
	}

	public String getContactApartment() {
		return contactApartment;
	}

	public void setContactApartment(String contactApartment) {
		this.contactApartment = contactApartment;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getContactPhone2() {
		return contactPhone2;
	}

	public void setContactPhone2(String contactPhone2) {
		this.contactPhone2 = contactPhone2;
	}

	public String getContactFacebook() {
		return contactFacebook;
	}

	public void setContactFacebook(String contactFacebook) {
		this.contactFacebook = contactFacebook;
	}

	public String getContactTwitter() {
		return contactTwitter;
	}

	public void setContactTwitter(String contactTwitter) {
		this.contactTwitter = contactTwitter;
	}

	public String getContactSkype() {
		return contactSkype;
	}

	public void setContactSkype(String contactSkype) {
		this.contactSkype = contactSkype;
	}

	public Integer getMasterCountry() {
		return masterCountry;
	}

	public void setMasterCountry(Integer masterCountry) {
		this.masterCountry = masterCountry;
	}

	public Integer getMasterCity() {
		return masterCity;
	}

	public void setMasterCity(Integer masterCity) {
		this.masterCity = masterCity;
	}

	public Integer getMasterUniversity() {
		return masterUniversity;
	}

	public void setMasterUniversity(Integer masterUniversity) {
		this.masterUniversity = masterUniversity;
	}

	public String getMasterUniversityName() {
		return masterUniversityName;
	}

	public void setMasterUniversityName(String masterUniversityName) {
		this.masterUniversityName = masterUniversityName;
	}

	public Integer getMasterSpeciality() {
		return masterSpeciality;
	}

	public void setMasterSpeciality(Integer masterSpeciality) {
		this.masterSpeciality = masterSpeciality;
	}

	public String getMasterProfession() {
		return masterProfession;
	}

	public void setMasterProfession(String masterProfession) {
		this.masterProfession = masterProfession;
	}

	public Integer getMasterProfGroup() {
		return masterProfGroup;
	}

	public void setMasterProfGroup(Integer masterProfGroup) {
		this.masterProfGroup = masterProfGroup;
	}

	public String getMasterDiplomaNumber() {
		return masterDiplomaNumber;
	}

	public void setMasterDiplomaNumber(String masterDiplomaNumber) {
		this.masterDiplomaNumber = masterDiplomaNumber;
	}

	public Date getMasterDiplomaDate() {
		return masterDiplomaDate;
	}

	public void setMasterDiplomaDate(Date masterDiplomaDate) {
		this.masterDiplomaDate = masterDiplomaDate;
	}

	public String getAcademicsSpecialityTheme() {
		return academicsSpecialityTheme;
	}

	public void setAcademicsSpecialityTheme(String academicsSpecialityTheme) {
		this.academicsSpecialityTheme = academicsSpecialityTheme;
	}

	public Integer getAcademicsDuration() {
		return academicsDuration;
	}

	public void setAcademicsDuration(Integer academicsDuration) {
		this.academicsDuration = academicsDuration;
	}

	public Integer getAcademicsStatus() {
		return academicsStatus;
	}

	public void setAcademicsStatus(Integer academicsStatus) {
		this.academicsStatus = academicsStatus;
	}

	public Date getAcademicsStatusDate() {
		return academicsStatusDate;
	}

	public void setAcademicsStatusDate(Date academicsStatusDate) {
		this.academicsStatusDate = academicsStatusDate;
	}

	public String getAcademicsStatusNotes() {
		return academicsStatusNotes;
	}

	public void setAcademicsStatusNotes(String academicsStatusNotes) {
		this.academicsStatusNotes = academicsStatusNotes;
	}

	public Integer getAcademicsForm() {
		return academicsForm;
	}

	public void setAcademicsForm(Integer academicsForm) {
		this.academicsForm = academicsForm;
	}

	public Integer getAcademicsPayment() {
		return academicsPayment;
	}

	public void setAcademicsPayment(Integer academicsPayment) {
		this.academicsPayment = academicsPayment;
	}

	public Integer getAcademicsReasonsForAcc() {
		return academicsReasonsForAcc;
	}

	public void setAcademicsReasonsForAcc(Integer academicsReasonsForAcc) {
		this.academicsReasonsForAcc = academicsReasonsForAcc;
	}

	public Integer getAcademicsChangeUniversity() {
		return academicsChangeUniversity;
	}

	public void setAcademicsChangeUniversity(Integer academicsChangeUniversity) {
		this.academicsChangeUniversity = academicsChangeUniversity;
	}

	public Date getAcademicsYearOfAdmission() {
		return academicsYearOfAdmission;
	}

	public void setAcademicsYearOfAdmission(Date academicsYearOfAdmission) {
		this.academicsYearOfAdmission = academicsYearOfAdmission;
	}

	public Date getAcademicsCalendarYearOfAdmission() {
		return academicsCalendarYearOfAdmission;
	}

	public void setAcademicsCalendarYearOfAdmission(
			Date academicsCalendarYearOfAdmission) {
		this.academicsCalendarYearOfAdmission = academicsCalendarYearOfAdmission;
	}

	public Date getAcademicsYearOfGraduation() {
		return academicsYearOfGraduation;
	}

	public void setAcademicsYearOfGraduation(Date academicsYearOfGraduation) {
		this.academicsYearOfGraduation = academicsYearOfGraduation;
	}

	public Boolean getAcademicsScholarship() {
		return academicsScholarship;
	}

	public void setAcademicsScholarship(Boolean academicsScholarship) {
		this.academicsScholarship = academicsScholarship;
	}

	public Boolean getAcademicsHostel() {
		if (academicsHostel == null) {
			academicsHostel = false;
		}
		return academicsHostel;
	}

	public void setAcademicsHostel(Boolean academicsHostel) {
		if (academicsHostel == null) {
			academicsHostel = false;
		}
		this.academicsHostel = academicsHostel;
	}

	public Boolean getAcademicsHolidays() {
		if (academicsHolidays == null) {
			academicsHolidays = false;
		}
		return academicsHolidays;
	}

	public void setAcademicsHolidays(Boolean academicsHolidays) {
		if (academicsHolidays == null) {
			academicsHolidays = false;
		}
		this.academicsHolidays = academicsHolidays;
	}

	public Boolean getAcademicsInternationalProgram() {
		if (academicsInternationalProgram == null) {
			academicsInternationalProgram = false;
		}
		return academicsInternationalProgram;
	}

	public void setAcademicsInternationalProgram(
			Boolean academicsInternationalPrograms) {
		if (academicsInternationalPrograms == null) {
			academicsInternationalPrograms = false;
		}
		this.academicsInternationalProgram = academicsInternationalPrograms;
	}

	public Date getAcademicsInternationalProgramStart() {
		return academicsInternationalProgramStart;
	}

	public void setAcademicsInternationalProgramStart(
			Date academicsInternationalProgramStart) {
		this.academicsInternationalProgramStart = academicsInternationalProgramStart;
	}

	public Date getAcademicsInternationalProgramEnd() {
		return academicsInternationalProgramEnd;
	}

	public void setAcademicsInternationalProgramEnd(
			Date academicsInternationalProgramEnd) {
		this.academicsInternationalProgramEnd = academicsInternationalProgramEnd;
	}

	public Integer getAcademicsFaculty() {
		return academicsFaculty;
	}

	public void setAcademicsFaculty(Integer academicsFaculty) {
		this.academicsFaculty = academicsFaculty;
	}

	public Integer getAcademicsDepartment() {
		if (academicsDepartment == null) {
			return 1;
		}
		return academicsDepartment;
	}

	public void setAcademicsDepartment(Integer academicsDepartment) {
		this.academicsDepartment = academicsDepartment;
	}

	public String getAcademicsFacultyNumber() {
		return academicsFacultyNumber;
	}

	public void setAcademicsFacultyNumber(String academicsFacultyNumber) {
		this.academicsFacultyNumber = academicsFacultyNumber;
	}

	public Integer getAcademicsSpeciality() {
		return academicsSpeciality;
	}

	public void setAcademicsSpeciality(Integer academicsSpecialty) {
		this.academicsSpeciality = academicsSpecialty;
	}

	public Integer getAcademicsSpecialityNSI() {
		return academicsSpecialityNSI;
	}

	public void setAcademicsSpecialityNSI(Integer academicsSpecialtyNSI) {
		this.academicsSpecialityNSI = academicsSpecialtyNSI;
	}

	public String getAcademicsSpecialitySubjectCode() {
		return academicsSpecialitySubjectCode;
	}

	public void setAcademicsSpecialitySubjectCode(
			String academicsSpecialitySubjectCode) {
		this.academicsSpecialitySubjectCode = academicsSpecialitySubjectCode;
	}

	public Integer getAcademicsProfGroup() {
		return academicsProfGroup;
	}

	public void setAcademicsProfGroup(Integer academicsProfGroup) {
		this.academicsProfGroup = academicsProfGroup;
	}

	public Integer getAcademicsCourse() {
		return academicsCourse;
	}

	public void setAcademicsCourse(Integer academicsCourse) {
		this.academicsCourse = academicsCourse;
	}

	public String getAcademicsWorkingLanguage() {
		return academicsWorkingLanguage;
	}

	public void setAcademicsWorkingLanguage(String academicsWorkingLanguage) {
		this.academicsWorkingLanguage = academicsWorkingLanguage;
	}

	public Boolean getAcademicsDegreeWithAnotherUniversity() {
		return academicsDegreeWithAnotherUniversity;
	}

	public void setAcademicsDegreeWithAnotherUniversity(
			Boolean academicsDegreeWithAnotherUniversity) {
		if (academicsDegreeWithAnotherUniversity == null) {
			academicsDegreeWithAnotherUniversity = false;
		}
		this.academicsDegreeWithAnotherUniversity = academicsDegreeWithAnotherUniversity;
	}

	public Boolean getAcademicsNewSpecialty() {
		if (academicsNewSpecialty == null) {
			return false;
		}
		return academicsNewSpecialty;
	}

	public void setAcademicsNewSpecialty(Boolean academicsNewSpecialty) {
		if (academicsNewSpecialty == null) {
			academicsNewSpecialty = false;
		}
		this.academicsNewSpecialty = academicsNewSpecialty;
	}

	public Boolean getAcademicsReadyForReportMes() {
		return academicsReadyForReportMes;
	}

	public void setAcademicsReadyForReportMes(Boolean academicsReadyForReportMes) {
		this.academicsReadyForReportMes = academicsReadyForReportMes;
	}
}