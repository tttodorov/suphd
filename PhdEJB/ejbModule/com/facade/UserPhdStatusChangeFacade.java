package com.facade;

import java.util.List;

import javax.ejb.Local;

import com.model.PhdStatusChange;

@Local
public interface UserPhdStatusChangeFacade {

	public PhdStatusChange getById(Integer id);

	public List<PhdStatusChange> getAllByUser(Integer userId);

	public PhdStatusChange getLastByUser(Integer userId);
}