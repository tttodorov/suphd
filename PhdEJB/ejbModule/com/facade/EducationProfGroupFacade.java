package com.facade;

import javax.ejb.Local;

import com.model.EducationProfGroup;

@Local
public interface EducationProfGroupFacade {

	public EducationProfGroup getById(Integer id);
}
