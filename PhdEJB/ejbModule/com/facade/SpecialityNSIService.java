package com.facade;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.model.SpecialityNSI;

@WebService
@SOAPBinding(style = Style.RPC)
public interface SpecialityNSIService {

	@WebMethod(operationName = "getById")
	public SpecialityNSI getById(Integer id);
	
	@WebMethod(operationName = "getAll")
	public List<SpecialityNSI> getAll();
}
