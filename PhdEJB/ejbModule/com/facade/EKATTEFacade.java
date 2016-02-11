package com.facade;

import javax.ejb.Local;

import com.model.EKATTE;

@Local
public interface EKATTEFacade {
	
	public EKATTE getById(Integer id);
	
}
