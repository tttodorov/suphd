package com.facade;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import com.dao.CountryDAO;
import com.model.Country;

@Stateless
@WebService(endpointInterface = "com.facade.CountryService", serviceName = "CountryService")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public class CountryServiceImp implements CountryService {

	@EJB
	private CountryDAO countryDAO;

	public CountryServiceImp() {
	}

	@Override
	public List<Country> findAllMes() {
		return countryDAO.getAll();
	}

	@Override
	public Country getById(Integer id) {
		return countryDAO.find(id);
	}

	@Override
	public Country getByCountryName(String countryName) {
		return countryDAO.getByCountryName(countryName);
	}

	@Override
	public List<Country> getAllSortByMesName() {
		return countryDAO.getAllSortByMesName();
	}
}