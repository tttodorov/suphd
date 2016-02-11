package com.user.phd;

import java.io.Serializable;
import java.util.ResourceBundle;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import com.facade.UserFacade;
import com.model.User;

@RequestScoped
@ManagedBean(name = "userPhdSpecialty", eager = true)
public class UserPhdSpecialtyBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private UserFacade userFacade;

	private User user;

	FacesContext context = FacesContext.getCurrentInstance();
	HttpServletRequest request = (HttpServletRequest) context
			.getExternalContext().getRequest();

	private ResourceBundle bundle = context.getApplication().getResourceBundle(
			context, "msgs");

	// get the remote user
	@ManagedProperty("#{request.remoteUser}")
	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public User getUser() {
		if (user == null) {
			ExternalContext context = FacesContext.getCurrentInstance()
					.getExternalContext();
			String username = context.getUserPrincipal().getName();
			user = userFacade.findByUsername(username);
		}
		return user;
	}

	public void updateUser() {
		try {
			userFacade.update(user);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, bundle
							.getString("savedChanges"), null));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}