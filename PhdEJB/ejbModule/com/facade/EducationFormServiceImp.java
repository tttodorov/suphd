package com.facade;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import com.dao.EducationFormDAO;
import com.model.EducationForm;

@Stateless
@WebService(endpointInterface = "com.facade.EducationFormService", serviceName = "EducationFormService")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public class EducationFormServiceImp implements EducationFormService {

	@EJB
	private EducationFormDAO educationFormDAO;

	public EducationFormServiceImp() {
	}

	@Override
	public EducationForm getById(Integer id) {
		return educationFormDAO.find(id);
	}
	
	@Override
	public List<EducationForm> getAll() {
		return educationFormDAO.getAll();
	}
}