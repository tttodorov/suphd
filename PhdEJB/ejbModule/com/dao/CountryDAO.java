package com.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.model.Country;
import com.model.Log;

@Stateless
public class CountryDAO extends GenericDAO<Country> {

	private final static String UNIT_NAME_MES = "MesPU";
	
	@PersistenceContext(unitName = UNIT_NAME_MES)
	private EntityManager emMes;

	public CountryDAO() {
		super(Country.class);
	}

	@SuppressWarnings({ "unchecked" })
	public List<Country> getAll() {
		List<Country> codeCountries = new ArrayList<Country>();
		try {
			String namedQuery = "SELECT country FROM Country country ORDER BY country.id ASC";
			Query query = emMes.createQuery(namedQuery);
			codeCountries = (ArrayList<Country>) query.getResultList();
		} catch (Exception e) {
			@SuppressWarnings("unused")
			Log log = new Log("ERROR CountryDAO.findAllMes: " + e.getMessage());
		}
		return codeCountries;
	}
	
	public Country getById(Integer id) {
		Country result = new Country();
		try {
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("id", id);
			String namedQuery = "SELECT c FROM Country c WHERE c.id = :id";
			Query query = emMes.createQuery(namedQuery);
			if (parameters != null && !parameters.isEmpty()) {
				populateQueryParameters(query, parameters);
			}
			result = (Country) query.getSingleResult();
		} catch (Exception e) {
			@SuppressWarnings("unused")
			Log log = new Log("ERROR CountryDAO.getById: " + e.getMessage());
		}
		if (result == null) {
			return new Country();
		}
		return result;
	}

	public Country getByCountryName(String countryName) {
		Country result = new Country();
		try {
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("mesName", countryName);
			String namedQuery = "SELECT c FROM Country c WHERE c.mesName = :mesName";
			Query query = emMes.createQuery(namedQuery);
			if (parameters != null && !parameters.isEmpty()) {
				populateQueryParameters(query, parameters);
			}
			result = (Country) query.getSingleResult();
		} catch (Exception e) {
			@SuppressWarnings("unused")
			Log log = new Log("ERROR CountryDAO.getByCountryName: " + e.getMessage());
		}
		if (result == null) {
			return new Country();
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<Country> getAllSortByMesName() {
		List<Country> codeCountries = new ArrayList<Country>();
		try {
			String namedQuery = "SELECT country FROM Country country ORDER BY country.mesName ASC";
			Query query = emMes.createQuery(namedQuery);
			codeCountries = (ArrayList<Country>) query.getResultList();
		} catch (Exception e) {
			@SuppressWarnings("unused")
			Log log = new Log("CountryDAO.getAllSortByMesName: " + e.getMessage());
		}
		return codeCountries;
	}
}