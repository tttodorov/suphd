package com.user.adminUniversity;

import java.io.Serializable;
import java.util.ResourceBundle;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import com.facade.UserFacade;
import com.model.User;

@RequestScoped
@ManagedBean(name = "adminUniversityProfileAdmin", eager = true)
public class AdminUniversityProfileAdminBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private UserFacade userFacade;
	private User user;
	private Integer userPhdId;

	FacesContext context = FacesContext.getCurrentInstance();
	HttpServletRequest request = (HttpServletRequest) context
			.getExternalContext().getRequest();

	private ResourceBundle bundle = context.getApplication().getResourceBundle(
			context, "msgs");

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

	public User getUser() {
		if (user == null) {
			user = userFacade.getById(getUserPhdId());
		}
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getUserPhdId() {
		if (userPhdId == null) {
			HttpServletRequest req = (HttpServletRequest) FacesContext
					.getCurrentInstance().getExternalContext().getRequest();
			userPhdId = Integer.parseInt(req.getParameter("userId"));
		}
		return userPhdId;
	}

	public void setUserPhdId(Integer userPhdId) {
		this.userPhdId = userPhdId;
	}

	public String moveToListAllAdmins() {
		return "adminUniversityListAllAdmins";
	}

	public String moveToAdminUniversityHomePage() {
		return "adminUniversityHomePage";
	}

}