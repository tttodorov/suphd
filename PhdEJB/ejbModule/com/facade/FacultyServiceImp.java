package com.facade;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import com.dao.FacultyDAO;
import com.model.Faculty;

@Stateless
@WebService(endpointInterface = "com.facade.FacultyService", serviceName = "FacultyService")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public class FacultyServiceImp implements FacultyService {

	@EJB
	private FacultyDAO facultyDAO;

	public FacultyServiceImp() {
	}

	@Override
	public Faculty getById(Integer id) {
		return facultyDAO.find(id);
	}
	
	@Override
	public List<Faculty> getAll() {
		return facultyDAO.getAll();
	}
}