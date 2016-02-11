package com.facade;

import java.util.List;

import javax.ejb.Local;

import com.model.SpecialityNSI;

@Local
public interface SpecialityNSIFacade {

	public SpecialityNSI getById(Integer id);
	
	public List<SpecialityNSI> getAll();
}
