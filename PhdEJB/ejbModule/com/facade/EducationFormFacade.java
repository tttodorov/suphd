package com.facade;

import java.util.List;

import javax.ejb.Local;

import com.model.EducationForm;

@Local
public interface EducationFormFacade {

	public EducationForm getById(Integer id);
	
	public List<EducationForm> getAll();
}
