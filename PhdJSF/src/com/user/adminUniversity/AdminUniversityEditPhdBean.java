package com.user.adminUniversity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import com.LogBean;
import com.converter.MesObject;
import com.converter.MesService;
import com.facade.UserFacade;
import com.model.User;

@RequestScoped
@ManagedBean(name = "adminUniversityEditPhd", eager = true)
public class AdminUniversityEditPhdBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private UserFacade userFacade;
	private User user = new User();

	FacesContext context = FacesContext.getCurrentInstance();
	HttpServletRequest request = (HttpServletRequest) context
			.getExternalContext().getRequest();
	private ResourceBundle bundle = context.getApplication().getResourceBundle(
			context, "msgs");
	private String fromOutcome;

	// Converted nomenclatures
	private MesObject birthCity;
	private MesObject birthCountry;
	private MesObject countryIdNumber;
	private MesObject countryCitizenship1;
	private MesObject countryCitizenship2;
	private MesObject countryResidence;
	private MesObject EKATTEResidence;
	private MesObject masterCountry;
	private MesObject masterEKATTE;
	private MesObject masterProfGroup;
	private MesObject masterSpeciality;
	private MesObject masterUniversity;
	private MesObject educationForm;
	private MesObject educationDuration;
	private MesObject educationPayment;
	private MesObject educationStatus;
	private MesObject academicsFaculty;
	private MesObject academicsDepartment;
	private MesObject academicsSpeciality;
	private MesObject academicsSpecialityNSI;
	private MesObject suClass;
	private MesObject educationReasonForAcc;

	private String masterUniversityName;

	@PostConstruct
	public void init() {
		FacesContext context = FacesContext.getCurrentInstance();
		String viewId = context.getViewRoot().getViewId();
		Integer lastIndexOfSlash = viewId.lastIndexOf("/");
		Integer lastIndexOfDot = viewId.lastIndexOf(".");
		fromOutcome = viewId.substring(lastIndexOfSlash + 1, lastIndexOfDot);
		// Check for user.id in Request
		Map<String, String> parametersMap = context.getExternalContext()
				.getRequestParameterMap();
		try {
			Integer userId = Integer.parseInt(parametersMap.get("userId"));
			user = userFacade.getById(userId);
		} catch (Exception e) {
			if (e.getCause() != null) {
				System.out.println("AdminUniversityEditPhdBean.init.user: "
						+ e.getMessage());
			}
		}
	}

	private MesService mesService = new MesService();

	public List<MesObject> completeMesObject(String query,
			List<MesObject> mesObjects) {
		List<MesObject> allMesObjects = mesObjects;
		List<MesObject> filteredMesObjects = new ArrayList<MesObject>();
		for (int i = 0; i < allMesObjects.size(); i++) {
			try {
				MesObject skin = allMesObjects.get(i);
				if (skin.getMesName().toLowerCase().startsWith(query)) {
					filteredMesObjects.add(skin);
				}
			} catch (Exception e) {
				System.out
						.println("AdminUniversityEditUserBean.completeMesObject: "
								+ e.getMessage());
			}
		}
		return filteredMesObjects;
	}

	public List<MesObject> completeCountry(String query) {
		return completeMesObject(query, mesService.getMesCountries());
	}

	public List<MesObject> completeCity(String query) {
		return completeMesObject(query, mesService.getMesEkattes());
	}

	public List<MesObject> completeUniversity(String query) {
		return completeMesObject(query, mesService.getMesUniversities());
	}

	public List<MesObject> completeEducationForm(String query) {
		return completeMesObject(query, mesService.getMesEducationForms());
	}

	public List<MesObject> completeEducationPayment(String query) {
		return completeMesObject(query, mesService.getMesEducationPayments());
	}

	public List<MesObject> completeEducationDuration(String query) {
		return completeMesObject(query, mesService.getMesEducationDurations());
	}

	public List<MesObject> completeGroupsPhDFaculty(String query) {
		return completeMesObject(query, mesService.getMesGroupsPhDFaculties());
	}

	public List<MesObject> completeGroupsPhDDepartment(String query) {
		return completeMesObject(query, mesService.getMesGroupsPhDDepartments());
	}

	public List<MesObject> completeProfGroup(String query) {
		return completeMesObject(query, mesService.getMesEducationProfGroups());
	}

	public List<MesObject> completeSpeciality(String query) {
		return completeMesObject(query, mesService.getMesCodeSpecialities());
	}

	public List<MesObject> completeSpecialityNSI(String query) {
		return completeMesObject(query, mesService.getMesSpecialitiesNSI());
	}

	public List<MesObject> completeEducationStatus(String query) {
		return completeMesObject(query, mesService.getMesEducationStatuses());
	}

	public List<MesObject> completeSuClass(String query) {
		return completeMesObject(query, mesService.getSuClassesConverted());
	}

	public List<MesObject> completeEducationReasonForAcc(String query) {
		return completeMesObject(query, mesService.getMesEducationReasonsForAcc());
	}

	public String saveUser() {
		user.setIsActive(true);
		user.setAcademicsBanned(true);
		user.setRolePhd(true);
		if (birthCity != null) {
			user.setPersonalBirthCity(birthCity.getId());
		}
		if (birthCountry != null) {
			user.setPersonalBirthCountry(birthCountry.getId());
		}
		if (countryIdNumber != null) {
			user.setPersonalIdNumberCountry(countryIdNumber.getId());
		}
		if (countryCitizenship1 != null) {
			user.setPersonalCitizenship1(countryCitizenship1.getId());
		}
		if (countryCitizenship2 != null) {
			user.setPersonalCitizenship2(countryCitizenship2.getId());
		}
		if (countryResidence != null) {
			user.setContactCountry(countryResidence.getId());
		}
		if (EKATTEResidence != null) {
			user.setContactCity(EKATTEResidence.getId());
		}
		if (masterCountry != null) {
			user.setMasterCountry(masterCountry.getId());
		}
		if (masterEKATTE != null) {
			user.setMasterCity(masterEKATTE.getId());
		}
		if (masterUniversity != null) {
			user.setMasterUniversity(masterUniversity.getId());
			user.setMasterUniversityName(masterUniversity.getMesName());
		}
		if (educationForm != null) {
			user.setAcademicsForm(educationForm.getId());
		}
		if (educationDuration != null) {
			user.setAcademicsDuration(educationDuration.getId());
		}
		if (educationPayment != null) {
			user.setAcademicsPayment(educationPayment.getId());
		}
		if (academicsFaculty != null) {
			user.setAcademicsFaculty(academicsFaculty.getId());
		}
		if (academicsDepartment != null) {
			user.setAcademicsDepartment(academicsDepartment.getId());
		}
		if (academicsSpeciality != null) {
			user.setAcademicsSpeciality(academicsSpeciality.getId());
		}
		if (academicsSpecialityNSI != null) {
			user.setAcademicsSpecialityNSI(academicsSpecialityNSI.getId());
		}
		if (masterSpeciality != null) {
			user.setMasterSpeciality(masterSpeciality.getId());
		}
		if (masterProfGroup != null) {
			user.setMasterProfGroup(masterProfGroup.getId());
		}
		if (educationStatus != null) {
			user.setAcademicsStatus(educationStatus.getId());
		}
		if (suClass != null) {
			user.setAcademicsCourse(suClass.getId());
		}
		if (educationReasonForAcc != null) {
			user.setAcademicsReasonsForAcc(educationReasonForAcc.getId());
		}
		
		if (user.getId() != null) {
			userFacade.update(user);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, bundle
							.getString("savedChanges"), null));
			FacesContext.getCurrentInstance().getExternalContext().getFlash()
					.setKeepMessages(true);
			FacesContext facesContext = FacesContext.getCurrentInstance();
			String viewId = facesContext.getViewRoot().getViewId();
			String outcome = viewId.substring(viewId.lastIndexOf('/') + 1,
					viewId.lastIndexOf('.'));
			return outcome + "?userId=" + user.getId() + "faces-redirect=true";
		} else {
			if ((userFacade.getLast().getId() + 1) < 1000000) {
				user.setProfileUsername(String.format("su%06d", userFacade
						.getLast().getId()) + 1);
			} else {
				user.setProfileUsername(String.format("su%d", userFacade
						.getLast().getId() + 1));
			}
			userFacade.save(user);
		}
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, bundle
						.getString("newPhdCreated"), null));
		FacesContext.getCurrentInstance().getExternalContext().getFlash()
				.setKeepMessages(true);
		return "adminUniversityListPhds?faces-redirect=true";
	}

	public String deleteUser() {
		user.setIsActive(false);
		userFacade.update(user);
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, bundle
						.getString("phdDeleted"), null));
		FacesContext.getCurrentInstance().getExternalContext().getFlash()
				.setKeepMessages(true);
		return "adminUniversityListPhds?faces-redirect=true";
	}
	
	public String cancelUser() {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, bundle
						.getString("phdCancel"), null));
		FacesContext.getCurrentInstance().getExternalContext().getFlash()
				.setKeepMessages(true);
		return "adminUniversityListPhds?faces-redirect=true";
	}

	public Boolean isForReportMesCurrent() {
		if(userFacade.getAllUserPhdReportMes().contains(user)) {
			return true;
		}
		return false;
	}
	
	public String getPrevious(Integer userId) {
		try {
			User user = userFacade.getPreviousById(userId);
			userId = user.getId();
		} catch (NullPointerException npe) {
			@SuppressWarnings("unused")
			LogBean lBean = new LogBean("AdminUniversityEditPhd.getPrevious: "
					+ npe.getMessage());
		}
		return "adminUniversityEditPhdPersonalData?faces-redirect=true&includeViewParams=true&userId="
				+ userId;
	}

	public String getNext(Integer userId) {
		try {
			User user = userFacade.getNextById(userId);
			userId = user.getId();
		} catch (NullPointerException npe) {
			@SuppressWarnings("unused")
			LogBean lBean = new LogBean("AdminUniversityEditPhd.getNext: "
					+ npe.getMessage());
		}
		return "adminUniversityEditPhdPersonalData?faces-redirect=true&includeViewParams=true&userId="
				+ userId;
	}
	
	public void onUniversityChange() {
		if (masterUniversity != null && !masterUniversity.equals("")) {
			System.out.println("masterUniversity: "
					+ masterUniversity.getMesName());
			masterUniversityName = " ";
		}
	}

	public static String moveToAdminUniversityEditPhdAcademicData(Integer userId) {
		return "adminUniversityEditPhdAcademicData?faces-redirect=true&includeViewParams=true&userId="
				+ userId;
	}

	public static String moveToAdminUniversityEditPhdCitizenship(Integer userId) {
		return "adminUniversityEditPhdCitizenship?faces-redirect=true&includeViewParams=true&userId="
				+ userId;
	}

	public static String moveToAdminUniversityEditPhdContacts(Integer userId) {
		return "adminUniversityEditPhdContacts?faces-redirect=true&includeViewParams=true&userId="
				+ userId;
	}

	public static String moveToAdminUniversityEditPhdEducation(Integer userId) {
		return "adminUniversityEditPhdEducation?faces-redirect=true&includeViewParams=true&userId="
				+ userId;
	}

	public static String moveToAdminUniversityEditPhdPersonalData(Integer userId) {
		return "adminUniversityEditPhdPersonalData?faces-redirect=true&includeViewParams=true&userId="
				+ userId;
	}

	public static String moveToAdminUniversityEditPhdSpeciality(Integer userId) {
		return "adminUniversityEditPhdSpeciality?faces-redirect=true&includeViewParams=true&userId="
				+ userId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getFromOutcome() {
		return fromOutcome;
	}

	public void setFromOutcome(String fromOutcome) {
		this.fromOutcome = fromOutcome;
	}

	public MesObject getBirthCity() {
		return birthCity;
	}

	public void setBirthCity(MesObject birthCity) {
		this.birthCity = birthCity;
	}

	public MesObject getBirthCountry() {
		return birthCountry;
	}

	public void setBirthCountry(MesObject birthCountry) {
		this.birthCountry = birthCountry;
	}

	public MesObject getCountryIdNumber() {
		return countryIdNumber;
	}

	public void setCountryIdNumber(MesObject countryIdNumber) {
		this.countryIdNumber = countryIdNumber;
	}

	public MesObject getCountryCitizenship1() {
		return countryCitizenship1;
	}

	public void setCountryCitizenship1(MesObject countryCitizenship1) {
		this.countryCitizenship1 = countryCitizenship1;
	}

	public MesObject getCountryCitizenship2() {
		return countryCitizenship2;
	}

	public void setCountryCitizenship2(MesObject countryCitizenship2) {
		this.countryCitizenship2 = countryCitizenship2;
	}

	public MesObject getCountryResidence() {
		return countryResidence;
	}

	public void setCountryResidence(MesObject countryResidence) {
		this.countryResidence = countryResidence;
	}

	public MesObject getMasterCountry() {
		return masterCountry;
	}

	public void setMasterCountry(MesObject countryMaster) {
		this.masterCountry = countryMaster;
	}

	public MesObject getEKATTEResidence() {
		return EKATTEResidence;
	}

	public void setEKATTEResidence(MesObject eKATTEResidence) {
		EKATTEResidence = eKATTEResidence;
	}

	public MesObject getMasterEKATTE() {
		return masterEKATTE;
	}

	public void setMasterEKATTE(MesObject eKATTEMaster) {
		masterEKATTE = eKATTEMaster;
	}

	public MesObject getMasterProfGroup() {
		return masterProfGroup;
	}

	public void setMasterProfGroup(MesObject masterProfGroup) {
		this.masterProfGroup = masterProfGroup;
	}

	public MesObject getMasterUniversity() {
		return masterUniversity;
	}

	public void setMasterUniversity(MesObject universityMaster) {
		this.masterUniversity = universityMaster;
	}

	public MesObject getEducationForm() {
		return educationForm;
	}

	public void setEducationForm(MesObject educationFormCurrent) {
		this.educationForm = educationFormCurrent;
	}

	public MesObject getEducationDuration() {
		return educationDuration;
	}

	public void setEducationDuration(MesObject educationDurationCurrent) {
		this.educationDuration = educationDurationCurrent;
	}

	public MesObject getEducationPayment() {
		return educationPayment;
	}

	public void setEducationPayment(MesObject educationPaymentCurrent) {
		this.educationPayment = educationPaymentCurrent;
	}

	public MesObject getAcademicsFaculty() {
		return academicsFaculty;
	}

	public void setAcademicsFaculty(MesObject specialityFaculty) {
		this.academicsFaculty = specialityFaculty;
	}

	public MesObject getAcademicsDepartment() {
		return academicsDepartment;
	}

	public void setAcademicsDepartment(MesObject specialityDepartment) {
		this.academicsDepartment = specialityDepartment;
	}

	public MesObject getAcademicsSpeciality() {
		return academicsSpeciality;
	}

	public void setAcademicsSpeciality(MesObject specialityCurrent) {
		this.academicsSpeciality = specialityCurrent;
	}

	public MesObject getAcademicsSpecialityNSI() {
		return academicsSpecialityNSI;
	}

	public void setAcademicsSpecialityNSI(MesObject specialityNSICurrent) {
		this.academicsSpecialityNSI = specialityNSICurrent;
	}

	public MesObject getMasterSpeciality() {
		return masterSpeciality;
	}

	public void setMasterSpeciality(MesObject masterSpeciality) {
		this.masterSpeciality = masterSpeciality;
	}

	public MesObject getEducationStatus() {
		return educationStatus;
	}

	public void setEducationStatus(MesObject educationStatus) {
		this.educationStatus = educationStatus;
	}

	public String getMasterUniversityName() {
		return masterUniversityName;
	}

	public void setMasterUniversityName(String masterUniversityName) {
		this.masterUniversityName = masterUniversityName;
	}

	public MesObject getSuClass() {
		return suClass;
	}

	public void setSuClass(MesObject suClass) {
		this.suClass = suClass;
	}

	public MesObject getEducationReasonForAcc() {
		return educationReasonForAcc;
	}

	public void setEducationReasonForAcc(MesObject educationReasonForAcc) {
		this.educationReasonForAcc = educationReasonForAcc;
	}
}