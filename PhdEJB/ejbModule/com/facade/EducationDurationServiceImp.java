package com.facade;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import com.dao.EducationDurationDAO;
import com.model.EducationDuration;

@Stateless
@WebService(endpointInterface = "com.facade.EducationDurationService", serviceName = "EducationDurationService")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public class EducationDurationServiceImp implements EducationDurationService {

	@EJB
	private EducationDurationDAO educationDurationDAO;

	public EducationDurationServiceImp() {
	}

	@Override
	public EducationDuration getById(Integer id) {
		return educationDurationDAO.getById(id);
	}
	
	@Override
	public List<EducationDuration> getAll() {
		return educationDurationDAO.getAll();
	}
}