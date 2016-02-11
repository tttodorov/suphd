package com.facade;

import java.util.List;

import javax.ejb.Local;
import javax.jws.WebMethod;

import com.model.EducationPayment;

@Local
public interface EducationPaymentFacade {

	@WebMethod(operationName = "getById")
	public EducationPayment getById(Integer id);
	
	@WebMethod(operationName = "getAll")
	public List<EducationPayment> getAll();
}
