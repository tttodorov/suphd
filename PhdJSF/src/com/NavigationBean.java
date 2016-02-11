package com;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

@RequestScoped
@ManagedBean(name = "navigation", eager = true)
public class NavigationBean implements Serializable {

	private static final long serialVersionUID = 1L;

	public static String redirectToRoot() {
		ExternalContext ec = FacesContext.getCurrentInstance()
				.getExternalContext();
		return ec.getRequestContextPath();
	}

	public static String moveToHelpPage() {
		return "help?faces-redirect=true";
	}

	public static String moveToSelectRole() {
		return "selectRole?faces-redirect=true";
	}

	// Admin System
	public static String moveToAdminSystemHome() {
		return "adminSystemHome?faces-redirect=true";
	}

	public static String moveToAdminSystemEditUser() {
		return "adminSystemEditUser?faces-redirect=true";
	}

	// Admin University
	public static String moveToAdminUniversityHome() {
		return "adminUniversityHome?faces-redirect=true";
	}

	public static String moveToAdminUniversityListPhds() {
		return "adminUniversityListPhds?faces-redirect=true";
	}

	public static String moveToAdminUniversityEditPhdNew() {
		return "adminUniversityEditPhdNew?faces-redirect=true";
	}

	public static String moveToAdminUniversityEditPhdAcademicData() {
		return "adminUniversityEditPhdAcademicData?faces-redirect=true";
	}

	public static String moveToAdminUniversityEditPhdCitizenship() {
		return "adminUniversityEditPhdCitizenship?faces-redirect=true";
	}

	public static String moveToAdminUniversityEditPhdContacts() {
		return "adminUniversityEditPhdContacts?faces-redirect=true";
	}

	public static String moveToAdminUniversityEditPhdEducation() {
		return "adminUniversityEditPhdEducation?faces-redirect=true";
	}

	public static String moveToAdminUniversityEditPhdPersonalData() {
		return "adminUniversityEditPhdPersonalData?faces-redirect=true";
	}

	public static String moveToAdminUniversityEditPhdSpeciality() {
		return "adminUniversityEditPhdSpeciality?faces-redirect=true";
	}

	public static String moveToAdminUniversityListAdmins() {
		return "adminUniversityListAdmins?faces-redirect=true";
	}

	public static String moveToAdminUniversityReports() {
		return "adminUniversityReports?faces-redirect=true";
	}

	public static String moveToAdminUniversityReportMesExcel() {
		return "adminUniversityReportMesExcel?faces-redirect=true";
	}

	public String logOut() {
		getRequest().getSession().invalidate();
		return "logout";
	}

	private HttpServletRequest getRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance()
				.getExternalContext().getRequest();
	}
}