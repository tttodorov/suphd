package com.facade;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.dao.SpecialityDAO;
import com.model.Speciality;

@Stateless
public class SpecialityFacadeImp implements SpecialityFacade {

	@EJB
	private SpecialityDAO specialityDAO;

	@Override
	public Speciality getById(Integer id) {
		return specialityDAO.getById(id);
	}

	@Override
	public List<Speciality> getAllSpecialityReportMes() {
		return specialityDAO.getAllReportMes();
	}
}