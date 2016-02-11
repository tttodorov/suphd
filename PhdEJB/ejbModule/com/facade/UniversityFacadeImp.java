package com.facade;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.dao.UniversityDAO;
import com.model.University;

@Stateless
public class UniversityFacadeImp implements UniversityFacade {

	@EJB
	private UniversityDAO universityDAO;

	@Override
	public University getById(Integer id) {
		return universityDAO.getById(id);
	}
}