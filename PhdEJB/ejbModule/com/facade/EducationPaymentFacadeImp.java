package com.facade;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.dao.EducationPaymentDAO;
import com.model.EducationPayment;

@Stateless
public class EducationPaymentFacadeImp implements EducationPaymentFacade {

	@EJB
	private EducationPaymentDAO educationPaymentDAO;

	public EducationPaymentFacadeImp() {
	}

	@Override
	public EducationPayment getById(Integer id) {
		return educationPaymentDAO.getById(id);
	}
	
	@Override
	public List<EducationPayment> getAll() {
		return educationPaymentDAO.getAll();
	}
}