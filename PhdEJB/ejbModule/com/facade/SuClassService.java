package com.facade;


import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.model.SuClass;

@WebService
@SOAPBinding(style = Style.RPC)
public interface SuClassService {

	@WebMethod(operationName = "getById")
	public SuClass getById(Integer id);
	
	@WebMethod(operationName = "getSuClasses")
	public List<SuClass> getAll();

	@WebMethod(operationName = "getByMesId")
	public SuClass getByMesId(Integer mesId);
}
