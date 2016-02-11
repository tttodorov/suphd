package com.facade;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.dao.EducationClassDAO;
import com.model.EducationClass;

@Stateless
public class EducationClassFacadeImp implements EducationClassFacade {

	@EJB
	private EducationClassDAO educationClassDAO;

	@Override
	public EducationClass getById(Integer id) {
		return educationClassDAO.getById(id);
	}
	
	@Override
	public List<EducationClass> getAll() {
		return educationClassDAO.getAll();
	}
}