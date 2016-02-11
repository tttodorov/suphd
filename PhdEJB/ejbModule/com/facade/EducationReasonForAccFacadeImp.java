package com.facade;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.dao.EducationReasonForAccDAO;
import com.model.EducationReasonForAcc;

@Stateless
public class EducationReasonForAccFacadeImp implements EducationReasonForAccFacade {

	@EJB
	private EducationReasonForAccDAO educationReasonForAccDAO;

	@Override
	public EducationReasonForAcc getById(Integer id) {
		return educationReasonForAccDAO.getById(id);
	}

	@Override
	public List<EducationReasonForAcc> getAll() {
		return educationReasonForAccDAO.getAll();
	}
}