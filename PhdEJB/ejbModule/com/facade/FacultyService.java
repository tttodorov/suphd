package com.facade;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.model.Faculty;

@WebService
@SOAPBinding(style = Style.RPC)
public interface FacultyService {

	@WebMethod(operationName = "getById")
	public Faculty getById(Integer id);
	
	@WebMethod(operationName = "getAll")
	public List<Faculty> getAll();
}
