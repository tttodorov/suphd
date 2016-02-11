package com.facade;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.dao.EducationDurationDAO;
import com.model.EducationDuration;

@Stateless
public class EducationDurationFacadeImp implements EducationDurationFacade {

	@EJB
	private EducationDurationDAO educationDurationDAO;

	@Override
	public EducationDuration getById(Integer id) {
		return educationDurationDAO.getById(id);
	}
	
	@Override
	public List<EducationDuration> getAll() {
		return educationDurationDAO.getAll();
	}
}