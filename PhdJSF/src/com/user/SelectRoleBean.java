package com.user;

import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.LogBean;
import com.model.User;

@RequestScoped
@ManagedBean(name = "selectRole", eager = true)
public class SelectRoleBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private User user;

	@PostConstruct
	public void init() throws MalformedURLException {
		// Set current User id in Session
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();
		ExternalContext externalContext = context.getExternalContext();
		HttpSession httpSession = request.getSession(false);
		String viewId = context.getViewRoot().getViewId();		
		Integer lastIndexOfSlash = viewId.lastIndexOf("/");
		Integer lastIndexOfDot = viewId.lastIndexOf(".");
		String fromOutcome = viewId.substring(lastIndexOfSlash + 1, lastIndexOfDot);
		try {
			user = (User) httpSession.getAttribute("user");
			if(user.getId() == null) {
				@SuppressWarnings("unused")
				LogBean lBean = new LogBean("ERROR SelectRoleBean.init.user: no user initialized");
				AuthBean auth = new AuthBean();
				auth.logout();
			} else {
				@SuppressWarnings("unused")
				LogBean lBean = new LogBean("INFO SelectRoleBean.init.user.id: " + user.getId());				
			}
		} catch (Exception e) {
			@SuppressWarnings("unused")
			LogBean log = new LogBean("ERROR SelectRoleBean.init.user: " + e.getMessage());
			if(!fromOutcome.equalsIgnoreCase("login")) {
				try {
					externalContext.redirect(externalContext
							.getRequestContextPath());
				} catch (IOException ioe) {
					@SuppressWarnings("unused")
					LogBean logIoe = new LogBean("ERROR SelectRoleBean.init.user.ioe: "+ ioe);
				}
			}
		}
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String moveToHelpPage() {
		return "help";
	}

	public String moveToPersonalData() {
		return "userPhdPersonalData";
	}

	public String moveToUserScholarshipsPhd() {
		return "userScholarshipsPhd";
	}

	public String moveToUserHostelPhd() {
		return "userHostelPhd";
	}

	public String moveToAdminUniversityListAllPhd() {
		return "adminUniversityListAllPhd";
	}

	public String moveToSupervisorListAllPhd() {
		return "supervisorListAllPhd";
	}

	public String moveToInspectorFacultyHomePage() {
		return "inspectorFacultyHomePage";
	}

	public String moveToSelectRole() {
		return "selectRole";
	}

}