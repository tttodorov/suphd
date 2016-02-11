package com.user.inspectorFaculty;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.facade.FacultyFacade;
import com.facade.UserFacade;
import com.model.Faculty;
import com.model.User;

@RequestScoped
@ManagedBean(name = "inspectorFacultyHomePage", eager = true)
public class InspectorFacultyHomePageBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private UserFacade userFacade;
	private User user;
	private Integer userId;
	private FacultyFacade facultyFacade;
	private List<Faculty> faculties;

	FacesContext context = FacesContext.getCurrentInstance();
	HttpServletRequest request = (HttpServletRequest) context
			.getExternalContext().getRequest();

	@PostConstruct
	public void init() {
		faculties = facultyFacade.getAll();
		ExternalContext externalContext = FacesContext.getCurrentInstance()
				.getExternalContext();
		String uri = ((HttpServletRequest) externalContext.getRequest())
				.getRequestURI();

		System.out.println(uri);
		System.out.println("KOKOKO");

	}

	public User getUser() {
		user = userFacade.getById(getUserId());
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserFacade getUserFacade() {
		return userFacade;
	}

	public void setUserFacade(UserFacade userFacade) {
		this.userFacade = userFacade;
	}

	public Integer getUserId() {
		// Get session userID from Session
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();
		HttpSession httpSession = request.getSession(false);
		Integer currentUserId = (Integer) Integer.valueOf(httpSession
				.getAttribute("currentUserId").toString());
		userId = currentUserId;
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public List<Faculty> getFaculties() {
		return faculties;
	}

	public void setFaculties(List<Faculty> faculties) {
		this.faculties = faculties;
	}

}
