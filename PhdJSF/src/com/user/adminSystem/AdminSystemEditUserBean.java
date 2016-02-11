package com.user.adminSystem;

import java.io.Serializable;
import java.util.Map;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import com.facade.UserFacade;
import com.model.User;

@RequestScoped
@ManagedBean(name = "adminSystemEditUser", eager = true)
public class AdminSystemEditUserBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private UserFacade userFacade;
	private User user = new User();

	private String password;
	private String passwordConfirm;

	FacesContext context = FacesContext.getCurrentInstance();
	HttpServletRequest request = (HttpServletRequest) context
			.getExternalContext().getRequest();

	private ResourceBundle bundle = context.getApplication().getResourceBundle(
			context, "msgs");

	@PostConstruct
	public void init() {
		// Check for User.Id in Request
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> parametersMap = context.getExternalContext()
				.getRequestParameterMap();
		try {
			Integer userId = Integer.parseInt(parametersMap.get("userId"));
			user = userFacade.getById(userId);
		} catch (Exception e) {
			if (e.getCause() != null) {
				System.err.println("AdminSystemEditUserBean.init: "
						+ e.getMessage());
			}
		}
	}

	public String saveUser() {
		// password
		if (password.isEmpty()) {
			if (password.equals(passwordConfirm)) {
				user.setProfilePassword(password);
			} else {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, bundle
								.getString("changePasswordDoNotMatch"), null));
				return null;
			}
		}

		User usernameCheck = new User();
		try {
			usernameCheck = userFacade.findByUsername(user.getProfileUsername());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
			
		// create
		if (user.getId() == null) {
			if (usernameCheck != null) {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, bundle
								.getString("adminSystemEditUserUsernameTaken"),
								null));
				return null;
			} else {
				userFacade.save(user);
			}
		}

		// update
		else {
			if (usernameCheck != null) {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, bundle
								.getString("adminSystemEditUserUsernameTaken"),
								null));
				return null;
			} else {
				userFacade.update(user);
			}
		}
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, bundle
						.getString("savedChanges"), null));
		FacesContext.getCurrentInstance().getExternalContext().getFlash()
				.setKeepMessages(true);
		return "adminSystemHome?faces-redirect=true";
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}
}