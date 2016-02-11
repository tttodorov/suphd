package com.facade;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.dao.UserPhdStatusChangeDAO;
import com.model.PhdStatusChange;

@Stateless
public class UserPhdStatusChangeFacadeImp implements UserPhdStatusChangeFacade {

	@EJB
	private UserPhdStatusChangeDAO userPhdStatusChangeDAO;

	@Override
	public PhdStatusChange getById(Integer id) {
		return userPhdStatusChangeDAO.getById(id);
	}
	
	@Override
	public List<PhdStatusChange> getAllByUser(Integer userId) {
		return userPhdStatusChangeDAO.getAllByUser(userId);
	}

	@Override
	public PhdStatusChange getLastByUser(Integer userId) {
		return userPhdStatusChangeDAO.getLastByUser(userId);
	}
}