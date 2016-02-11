package com.facade;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.dao.FacultyDAO;
import com.model.Faculty;

@Stateless
public class FacultyFacadeImp implements FacultyFacade {

	@EJB
	private FacultyDAO facultyDAO;

	@Override
	public Faculty getById(Integer id) {
		return facultyDAO.getById(id);
	}

	@Override
	public List<Faculty> getAll() {
		return facultyDAO.getAll();
	}

	@Override
	public List<Faculty> getAllFacultiesReportMes() {
		return facultyDAO.getAllFacultiesReportMes();
	}
}