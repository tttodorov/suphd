package com.user.adminSystem;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import com.facade.UserFacade;
import com.model.User;

@RequestScoped
@ManagedBean(name = "adminSystemHome", eager = true)
public class AdminSystemHomeBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private UserFacade userFacade;
	private List<User> listAllUsers;
	public String[] roles = {"admin","adminUniversity"};
	
	FacesContext context = FacesContext.getCurrentInstance();
	HttpServletRequest request = (HttpServletRequest) context
			.getExternalContext().getRequest();

	@PostConstruct
	public void init() {
		listAllUsers = userFacade.findAll();
	}

	public List<User> getListAllUsers() {
		return listAllUsers;
	}

	public void setListAllUsers(List<User> listAllUsers) {
		this.listAllUsers = listAllUsers;
	}

	public String[] getRoles() {
		return roles;
	}

	public void setRoles(String[] roles) {
		this.roles = roles;
	}
}
