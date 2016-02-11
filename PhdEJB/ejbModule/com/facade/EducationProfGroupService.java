package com.facade;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.model.EducationProfGroup;

@WebService
@SOAPBinding(style = Style.RPC)
public interface EducationProfGroupService {

	@WebMethod(operationName = "getById")
	public EducationProfGroup getById(Integer id);
	
	@WebMethod(operationName = "getAll")
	public List<EducationProfGroup> getAll();
}
