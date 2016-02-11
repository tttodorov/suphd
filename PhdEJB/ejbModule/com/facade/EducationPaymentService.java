package com.facade;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.model.EducationPayment;

@WebService
@SOAPBinding(style = Style.RPC)
public interface EducationPaymentService {

	@WebMethod(operationName = "getById")
	public EducationPayment getById(Integer id);
	
	@WebMethod(operationName = "getAll")
	public List<EducationPayment> getAll();
}
