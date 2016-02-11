package com.facade;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.dao.SpecialityNSIDAO;
import com.model.SpecialityNSI;

@Stateless
public class SpecialityNSIFacadeImp implements SpecialityNSIFacade {

	@EJB
	private SpecialityNSIDAO specialityNSIDAO;

	@Override
	public SpecialityNSI getById(Integer id) {
		return specialityNSIDAO.getById(id);
	}
	
	@Override
	public List<SpecialityNSI> getAll() {
		return specialityNSIDAO.getAll();
	}
}