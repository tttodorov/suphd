package com.facade;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import com.dao.UniversityDAO;
import com.model.University;

@Stateless
@WebService(endpointInterface = "com.facade.UniversityService", serviceName = "UniversityService")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public class UniversityServiceImp implements UniversityService {

	@EJB
	private UniversityDAO universityDAO;

	public UniversityServiceImp() {
	}

	@Override
	public University getById(Integer id) {
		return universityDAO.find(id);
	}
	
	@Override
	public List<University> findAllMes() {
		return universityDAO.getAll();
	}

	@Override
	public University getByUniversityId(Integer universityId) {
		return universityDAO.getByMesId(universityId);
	}
}