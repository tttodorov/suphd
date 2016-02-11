package com.facade;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.model.EducationReasonForAcc;

@WebService
@SOAPBinding(style = Style.RPC)
public interface EducationReasonForAccService {

	@WebMethod(operationName = "getById")
	public EducationReasonForAcc getById(Integer id);
	
	@WebMethod(operationName = "getAll")
	public List<EducationReasonForAcc> getAll();
}
