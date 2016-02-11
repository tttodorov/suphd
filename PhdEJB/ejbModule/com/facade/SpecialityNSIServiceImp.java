package com.facade;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;


import com.dao.SpecialityNSIDAO;
import com.model.SpecialityNSI;

@Stateless
@WebService(endpointInterface = "com.facade.SpecialityNSIService", serviceName = "SpecialityNSIService")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public class SpecialityNSIServiceImp implements SpecialityNSIService {

	@EJB
	private SpecialityNSIDAO specialityNSIDAO;

	public SpecialityNSIServiceImp() {
	}

	@Override
	public SpecialityNSI getById(Integer id) {
		return specialityNSIDAO.getById(id);
	}
	
	@Override
	public List<SpecialityNSI> getAll() {
		return specialityNSIDAO.getAll();
	}
}