package com.facade;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.dao.CodeSpecialityAdminUniDAO;
import com.model.CodeSpecialityAdminUni;

@Stateless
public class CodeSpecialityAdminUniFacadeImp implements CodeSpecialityAdminUniFacade {

	@EJB
	private CodeSpecialityAdminUniDAO CodeSpecialityAdminUniDAO;

	@Override
	public CodeSpecialityAdminUni getById(Integer id) {
		return CodeSpecialityAdminUniDAO.getById(id);
	}
}