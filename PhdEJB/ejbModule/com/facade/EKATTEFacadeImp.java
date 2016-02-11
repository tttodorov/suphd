package com.facade;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.dao.EKATTEDAO;
import com.model.EKATTE;

@Stateless
public class EKATTEFacadeImp implements EKATTEFacade {

	@EJB
	private EKATTEDAO ekatteDAO;

	public EKATTE getById(Integer id) {
		return ekatteDAO.getById(id);
	}

}