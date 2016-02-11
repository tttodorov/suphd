package com.facade;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.model.EducationForm;

@WebService
@SOAPBinding(style = Style.RPC)
public interface EducationFormService {

	@WebMethod(operationName = "getById")
	public EducationForm getById(Integer id);
	
	@WebMethod(operationName = "getAll")
	public List<EducationForm> getAll();
}
