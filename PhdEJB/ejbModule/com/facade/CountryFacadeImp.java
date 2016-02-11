package com.facade;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.dao.CountryDAO;
import com.model.Country;

@Stateless
public class CountryFacadeImp implements CountryFacade {

	@EJB
	private CountryDAO countryDAO;

	public Country getById(Integer id) {
		return countryDAO.getById(id);
	}
}