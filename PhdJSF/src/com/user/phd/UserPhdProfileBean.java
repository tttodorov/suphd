package com.user.phd;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import com.facade.UserFacade;
import com.model.User;

@RequestScoped
@ManagedBean(name = "userPhdProfile", eager = true)
public class UserPhdProfileBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private User user;

	@EJB
	private UserFacade userFacade;

	public User getUser() {
		if (user == null) {
			ExternalContext context = FacesContext.getCurrentInstance()
					.getExternalContext();
			String username = context.getUserPrincipal().getName();
			user = userFacade.findByUsername(username);
		}
		return user;
	}

	// get current page name
	private String viewId = FacesContext.getCurrentInstance().getViewRoot()
			.getViewId();
	private String controlString = "/phd/";
	private int controlStringLenght = controlString.length();
	private int controlStringPosition = viewId.lastIndexOf(controlString);
	private String pageName = viewId.substring(controlStringPosition + controlStringLenght);

	public String getControlString() {
		return controlString;
	}

	public void setControlString(String controlString) {
		this.controlString = controlString;
	}

	public int getControlStringLenght() {
		return controlStringLenght;
	}

	public void setControlStringLenght(int controlStringLenght) {
		this.controlStringLenght = controlStringLenght;
	}

	public int getControlStringPosition() {
		return controlStringPosition;
	}

	public void setControlStringPosition(int controlStringPosition) {
		this.controlStringPosition = controlStringPosition;
	}

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	// get current page name ends
	
	public String moveToAcademicData() {
		return "userPhdAcademicData?faces-redirect=true";
	}

	public String moveToCitizenship() {
		return "userPhdCitizenship";
	}

	public String moveToContacts() {
		return "userPhdContacts";
	}

	public String moveToEducation() {
		return "userPhdEducation";
	}

	public String moveToPersonalData() {
		return "userPhdPersonalData";
	}

	public String moveToSpecialty() {
		return "userPhdSpecialty";
	}

	public String moveToHelpPage() {
		return "help";
	}

	public String moveToLinks() {
		return "userPhdLinks";
	}

	public String moveToReport() {
		return "userPhdReport";
	}

	public String moveToHelpProfile() {
		return "helpProfile";
	}
	public String moveToUserProfile(){
		return "userProfile";
	}
	public String moveToSelectRole(){
		return "selectRole";
	}
}