package com.facade;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.dao.EducationStatusDAO;
import com.model.EducationStatus;

@Stateless
public class EducationStatusFacadeImp implements EducationStatusFacade {

	@EJB
	private EducationStatusDAO educationStatusDAO;

	@Override
	public EducationStatus getById(Integer id) {
		return educationStatusDAO.getById(id);
	}
	
	@Override
	public List<EducationStatus> getAll() {
		return educationStatusDAO.getAll();
	}
}