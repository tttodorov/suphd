package com.facade;

import javax.ejb.Local;

import com.model.CodeSpecialityAdminUni;

@Local
public interface CodeSpecialityAdminUniFacade {

	public CodeSpecialityAdminUni getById(Integer id);
}