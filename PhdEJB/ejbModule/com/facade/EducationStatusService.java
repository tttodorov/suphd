package com.facade;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.model.EducationStatus;

@WebService
@SOAPBinding(style = Style.RPC)
public interface EducationStatusService {

	@WebMethod(operationName = "getById")
	public EducationStatus getById(Integer id);
	
	@WebMethod(operationName = "getAll")
	public List<EducationStatus> getAll();
}
