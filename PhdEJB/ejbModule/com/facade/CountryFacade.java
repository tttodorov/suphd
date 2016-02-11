package com.facade;

import javax.ejb.Local;

import com.model.Country;

@Local
public interface CountryFacade {

	public Country getById(Integer id);

}