package com.user.adminUniversity;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import com.facade.UserFacade;
import com.model.User;

import org.primefaces.model.LazyDataModel;

@SessionScoped
@ManagedBean(name = "adminUniversityListPhds", eager = true)
public class AdminUniversityListPhdsBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private UserFacade userFacade;
	private List<User> listAllUserPhd;

	private LazyDataModel<User> lazyModel;
	
	FacesContext context = FacesContext.getCurrentInstance();
	HttpServletRequest request = (HttpServletRequest) context
			.getExternalContext().getRequest();

	@PostConstruct
	public void init() {
		listAllUserPhd = userFacade.getAllUserPhd();
	}
	
	public LazyDataModel<User> getLazyModel() {
        return lazyModel;
    }

	public List<User> getListAllUserPhd() {
		return listAllUserPhd;
	}

	public void setListAllUserPhd(List<User> listAllUserPhd) {
		this.listAllUserPhd = listAllUserPhd;
	}
}