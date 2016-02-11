package com.user.supervisor;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.facade.UserFacade;
import com.model.User;

@RequestScoped
@ManagedBean(name = "supervisorListAllPhd", eager = true)
public class SupervisorListAllPhd implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private UserFacade userFacade;
	private List<User> listAllUserPhd;

	@PostConstruct
	public void init() {
		setListAllUserPhd(userFacade.getAllUserPhd());
	}

	public String moveToReviewsPhd() {
		return "reviewsPhd";
	}

	public List<User> getListAllUserPhd() {
		return listAllUserPhd;
	}

	public void setListAllUserPhd(List<User> listAllUserPhd) {
		this.listAllUserPhd = listAllUserPhd;
	}
}
