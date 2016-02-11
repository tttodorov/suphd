package com.facade;

import java.util.List;

import javax.ejb.Local;

import com.model.EducationDuration;

@Local
public interface EducationDurationFacade {

	public EducationDuration getById(Integer id);

	public List<EducationDuration> getAll();
}
