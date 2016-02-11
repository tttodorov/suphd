package com.facade;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.model.Group;

@WebService
@SOAPBinding(style = Style.RPC)
public interface GroupService {

	@WebMethod(operationName = "getById")
	public Group getById(Integer id);

	@WebMethod(operationName = "getAll")
	public List<Group> getAll();
}
