package com.facade;

import java.util.List;

import javax.ejb.Local;

import com.model.EducationType;

@Local
public interface EducationTypeFacade {

	public EducationType getById(Integer id);
	
	public List<EducationType> getAll();
}
