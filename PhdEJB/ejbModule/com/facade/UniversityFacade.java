package com.facade;

import javax.ejb.Local;

import com.model.University;

@Local
public interface UniversityFacade {

	public University getById(Integer id);
}
