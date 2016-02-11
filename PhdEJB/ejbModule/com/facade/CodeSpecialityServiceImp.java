package com.facade;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import com.dao.CodeSpecialityDAO;
import com.model.CodeSpeciality;

@Stateless
@WebService(endpointInterface = "com.facade.CodeSpecialityService", serviceName = "CodeSpecialityService")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public class CodeSpecialityServiceImp implements CodeSpecialityService {

	@EJB
	private CodeSpecialityDAO CodeSpecialityDAO;

	public CodeSpecialityServiceImp() {
	}

	@Override
	public CodeSpeciality getById(Integer id) {
		return CodeSpecialityDAO.find(id);
	}
	
	@Override
	public List<CodeSpeciality> getAll() {
		return CodeSpecialityDAO.getAll();
	}
}