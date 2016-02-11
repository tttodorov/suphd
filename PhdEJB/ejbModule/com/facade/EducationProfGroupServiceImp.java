package com.facade;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import com.dao.EducationProfGroupDAO;
import com.model.EducationProfGroup;

@Stateless
@WebService(endpointInterface = "com.facade.EducationProfGroupService", serviceName = "EducationProfGroupService")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public class EducationProfGroupServiceImp implements EducationProfGroupService {

	@EJB
	private EducationProfGroupDAO educationProfGroupDAO;

	public EducationProfGroupServiceImp() {
	}

	@Override
	public EducationProfGroup getById(Integer id) {
		return educationProfGroupDAO.find(id);
	}
	
	@Override
	public List<EducationProfGroup> getAll() {
		return educationProfGroupDAO.getAll();
	}
}