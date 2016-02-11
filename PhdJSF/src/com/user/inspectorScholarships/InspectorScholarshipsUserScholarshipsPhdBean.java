package com.user.inspectorScholarships;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import com.facade.UserFacade;
import com.model.User;

@SessionScoped
@ManagedBean(name = "inspectorScholarshipsUserScholarshipsPhd", eager = true)
public class InspectorScholarshipsUserScholarshipsPhdBean implements
		Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private UserFacade userFacade;
	private List<User> listAllUserPhd;
	private User user;

	FacesContext context = FacesContext.getCurrentInstance();
	HttpServletRequest request = (HttpServletRequest) context
			.getExternalContext().getRequest();

	private ResourceBundle bundle = context.getApplication().getResourceBundle(
			context, "msgs");

	@PostConstruct
	public void init() {
		listAllUserPhd = userFacade.getAllUserPhd();
	}

	public void updateUsers() {

		for (User user : getListAllUserPhd()) {

			try {
				userFacade.update(user);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<User> getListAllUserPhd() {
		return listAllUserPhd;
	}

	public void setListAllUserPhd(List<User> listAllUserPhd) {
		this.listAllUserPhd = listAllUserPhd;
	}

	public void update() {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, bundle
						.getString("savedChanges"), null));
		for (User user : listAllUserPhd) {
			userFacade.update(user);
		}
	}
}
