package com.facade;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.model.EducationDuration;

@WebService
@SOAPBinding(style = Style.RPC)
public interface EducationDurationService {

	@WebMethod(operationName = "getById")
	public EducationDuration getById(Integer id);
	
	@WebMethod(operationName = "getAll")
	public List<EducationDuration> getAll();
}
