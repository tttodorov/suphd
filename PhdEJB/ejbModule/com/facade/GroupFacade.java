package com.facade;

import javax.ejb.Local;

import com.model.Group;

@Local
public interface GroupFacade {

	public Group getById(Integer id);
}
