package com.facade;

import javax.ejb.Local;

import com.model.CodeSpeciality;

@Local
public interface CodeSpecialityFacade {

	public CodeSpeciality getById(Integer id);
}
