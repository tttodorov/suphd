package com.facade;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.dao.EducationFormDAO;
import com.model.EducationForm;

@Stateless
public class EducationFormFacadeImp implements EducationFormFacade {

	@EJB
	private EducationFormDAO educationFormDAO;

	@Override
	public EducationForm getById(Integer id) {
		return educationFormDAO.getById(id);
	}
	
	@Override
	public List<EducationForm> getAll() {
		return educationFormDAO.getAll();
	}
}