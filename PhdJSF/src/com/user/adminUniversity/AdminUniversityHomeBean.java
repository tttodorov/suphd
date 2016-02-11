package com.user.adminUniversity;

import java.io.IOException;
import java.io.Serializable;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.LogBean;
import com.model.User;
import com.user.AuthBean;

@ManagedBean(name = "adminUniversityHome", eager = true)
@SessionScoped
public class AdminUniversityHomeBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private User user;

	private FacesContext context = FacesContext.getCurrentInstance();
	private ResourceBundle bundle = context.getApplication().getResourceBundle(
			context, "msgs");

	@PostConstruct
	public void init() {
		// Set current User id in Session
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();
		ExternalContext externalContext = context.getExternalContext();
		HttpSession httpSession = request.getSession(false);
		String viewId = context.getViewRoot().getViewId();
		Integer lastIndexOfSlash = viewId.lastIndexOf("/");
		Integer lastIndexOfDot = viewId.lastIndexOf(".");
		String fromOutcome = viewId.substring(lastIndexOfSlash + 1,
				lastIndexOfDot);
		try {
			user = (User) httpSession.getAttribute("user");
			if (user.getId() == null) {
				@SuppressWarnings("unused")
				LogBean lBean = new LogBean(
						"ERROR AdminUniversityHomeBean.init.user: no user initialized");
				AuthBean auth = new AuthBean();
				auth.logout();
			} else {
				@SuppressWarnings("unused")
				LogBean lBean = new LogBean(
						"INFO AdminUniversityHomeBean.init.user.id: "
								+ user.getId());
			}
		} catch (Exception e) {
			@SuppressWarnings("unused")
			LogBean log = new LogBean(
					"ERROR AdminUniversityHomeBean.init.user: "
							+ e.getMessage());
			if (!fromOutcome.equalsIgnoreCase("login")) {
				try {
					externalContext.redirect(externalContext
							.getRequestContextPath());
				} catch (IOException ioe) {
					@SuppressWarnings("unused")
					LogBean logIoe = new LogBean(
							"ERROR AdminUniversityHomeBean.init.user.ioe: "
									+ ioe);
				}
			}
			AuthBean auth = new AuthBean();
			auth.logout();
		}
		if (user.getRoleAdminUniversity() != true) {
			context.addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, bundle
							.getString("loginWellcome"), null));
			AuthBean authBean = new AuthBean();
			authBean.logout();
		}
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}