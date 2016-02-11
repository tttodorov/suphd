package com.facade;

import javax.ejb.EJB;
import javax.ejb.Stateless;


import com.dao.GroupDAO;
import com.model.Group;

@Stateless
public class GroupFacadeImp implements GroupFacade {

	@EJB
	private GroupDAO groupsDAO;

	@Override
	public Group getById(Integer id) {
		return groupsDAO.getById(id);
	}
}