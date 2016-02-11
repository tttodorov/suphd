package com.facade;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import com.dao.EducationStatusDAO;
import com.model.EducationStatus;

@Stateless
@WebService(endpointInterface = "com.facade.EducationStatusService", serviceName = "EducationStatusService")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public class EducationStatusServiceImp implements EducationStatusService {

	@EJB
	private EducationStatusDAO educationStatusDAO;

	public EducationStatusServiceImp() {
	}

	@Override
	public EducationStatus getById(Integer id) {
		return educationStatusDAO.find(id);
	}
	
	@Override
	public List<EducationStatus> getAll() {
		return educationStatusDAO.getAll();
	}
}