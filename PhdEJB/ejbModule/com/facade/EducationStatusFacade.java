package com.facade;

import java.util.List;

import javax.ejb.Local;

import com.model.EducationStatus;

@Local
public interface EducationStatusFacade {

	public EducationStatus getById(Integer id);
	
	public List<EducationStatus> getAll();
}
