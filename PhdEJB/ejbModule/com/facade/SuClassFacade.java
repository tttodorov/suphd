package com.facade;

import java.util.List;

import javax.ejb.Local;

import com.model.SuClass;

@Local
public interface SuClassFacade {

	public SuClass getById(Integer id);
	
	public List<SuClass> getAll();
}
