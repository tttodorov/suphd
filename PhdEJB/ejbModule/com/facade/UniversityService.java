package com.facade;


import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.model.University;

@WebService
@SOAPBinding(style = Style.RPC)
public interface UniversityService {

	@WebMethod(operationName = "getById")
	public University getById(Integer id);
	
	@WebMethod(operationName = "getUniversities")
	public List<University> findAllMes();

	@WebMethod(operationName = "getByMesId")
	public University getByUniversityId(Integer universityId);
}
