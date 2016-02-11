package com.facade;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.dao.EducationTypeDAO;
import com.model.EducationType;

@Stateless
public class EducationTypeFacadeImp implements EducationTypeFacade {

	@EJB
	private EducationTypeDAO educationTypeDAO;

	@Override
	public EducationType getById(Integer id) {
		return educationTypeDAO.getById(id);
	}
	
	@Override
	public List<EducationType> getAll() {
		return educationTypeDAO.getAll();
	}
}