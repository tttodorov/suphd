package com.facade;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import com.dao.SuClassDAO;
import com.model.SuClass;

@Stateless
@WebService(endpointInterface = "com.facade.SuClassService", serviceName = "SuClassService")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public class SuClassServiceImp implements SuClassService {

	@EJB
	private SuClassDAO suClassDAO;

	public SuClassServiceImp() {
	}

	@Override
	public SuClass getById(Integer id) {
		return suClassDAO.find(id);
	}

	@Override
	public List<SuClass> getAll() {
		return suClassDAO.getAll();
	}

	@Override
	public SuClass getByMesId(Integer mesId) {
		return suClassDAO.getByMesId(mesId);
	}
}