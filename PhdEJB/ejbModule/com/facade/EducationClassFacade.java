package com.facade;

import java.util.List;

import javax.ejb.Local;

import com.model.EducationClass;

@Local
public interface EducationClassFacade {

	public EducationClass getById(Integer id);
	
	public List<EducationClass> getAll();
}
