package com.facade;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.dao.CodeSpecialityDAO;
import com.model.CodeSpeciality;

@Stateless
public class CodeSpecialityFacadeImp implements CodeSpecialityFacade {

	@EJB
	private CodeSpecialityDAO CodeSpecialityDAO;

	@Override
	public CodeSpeciality getById(Integer id) {
		return CodeSpecialityDAO.getById(id);
	}
}