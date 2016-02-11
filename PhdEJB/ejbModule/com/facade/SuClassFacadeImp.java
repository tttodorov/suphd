package com.facade;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.dao.SuClassDAO;
import com.model.SuClass;

@Stateless
public class SuClassFacadeImp implements SuClassFacade {

	@EJB
	private SuClassDAO suClassDAO;

	@Override
	public SuClass getById(Integer id) {
		return suClassDAO.getById(id);
	}
	
	@Override
	public List<SuClass> getAll() {
		return suClassDAO.getAll();
	}
}