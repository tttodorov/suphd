package com.facade;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import com.dao.EducationPaymentDAO;
import com.model.EducationPayment;

@Stateless
@WebService(endpointInterface = "com.facade.EducationPaymentService", serviceName = "EducationPaymentService")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public class EducationPaymentServiceImp implements EducationPaymentService {

	@EJB
	private EducationPaymentDAO educationPaymentDAO;

	public EducationPaymentServiceImp() {
	}

	@Override
	public EducationPayment getById(Integer id) {
		return educationPaymentDAO.find(id);
	}
	
	@Override
	public List<EducationPayment> getAll() {
		return educationPaymentDAO.getAll();
	}
}