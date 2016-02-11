package com.facade;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import com.dao.EKATTEDAO;
import com.model.EKATTE;

@Stateless
@WebService(endpointInterface = "com.facade.EKATTEService", serviceName = "EKATTEService")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public class EKATTEServiceImp implements EKATTEService {

	@EJB
	private EKATTEDAO ekatteDAO;

	public EKATTEServiceImp() {
	}

	@Override
	public EKATTE getById(Integer id) {
		return ekatteDAO.getById(id);
	}
	
	@Override
	public List<EKATTE> findAllMes() {
		return ekatteDAO.getAll();
	}

	@Override
	public List<EKATTE> getAllSortByMesName() {
		return ekatteDAO.getAllSortByMesName();
	}	

	@Override
	public List<EKATTE> getAllSortByMunicipality() {
		return ekatteDAO.getAllSortByMunicipality();
	}
	
}