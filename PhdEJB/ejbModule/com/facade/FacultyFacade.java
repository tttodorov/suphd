package com.facade;

import java.util.List;

import javax.ejb.Local;

import com.model.Faculty;

@Local
public interface FacultyFacade {

	public Faculty getById(Integer id);

	public List<Faculty> getAll();
	
	public List<Faculty> getAllFacultiesReportMes();
}
