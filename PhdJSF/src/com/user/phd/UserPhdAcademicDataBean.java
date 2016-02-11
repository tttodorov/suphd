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
@ManagedBean(name = "userPhdAcademicData", eager = true)
public class UserPhdAcademicDataBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private UserFacade userFacade;
	private User user;

	public void updateUser() {
		try {
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
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

	public void setUser(User user) {
		this.user = user;
	}
}