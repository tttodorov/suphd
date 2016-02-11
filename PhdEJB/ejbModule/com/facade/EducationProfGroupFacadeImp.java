package com.facade;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.dao.EducationProfGroupDAO;
import com.model.EducationProfGroup;

@Stateless
public class EducationProfGroupFacadeImp implements EducationProfGroupFacade {

	@EJB
	private EducationProfGroupDAO educationProfGroupDAO;

	@Override
	public EducationProfGroup getById(Integer id) {
		return educationProfGroupDAO.getById(id);
	}
}