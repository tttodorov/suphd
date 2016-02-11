package com.user.adminUniversity;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.facade.UserFacade;
import com.model.User;

@RequestScoped
@ManagedBean(name = "adminUniversityListAllAdmins", eager = true)
public class AdminUniversityListAdminsBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private UserFacade userFacade;
	private List<User> listAllUserAdmin;

	@PostConstruct
	public void init() {
		setListAllUserAdmin(userFacade.findAllUserAdmin());
	}

	public String moveToProfileAdmin() {
		return "adminUniversityProfileAdmin";
	}

	public List<User> getListAllUserAdmin() {
		return listAllUserAdmin;
	}

	public void setListAllUserAdmin(List<User> listAllUserAdmin) {
		this.listAllUserAdmin = listAllUserAdmin;
	}

	public String moveToAdminUniversityHomePage() {
		return "adminUniversityHomePage";
	}

}
