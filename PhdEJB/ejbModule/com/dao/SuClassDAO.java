package com.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.model.Log;
import com.model.SuClass;

@Stateless
public class SuClassDAO extends GenericDAO<SuClass> {

	private final static String UNIT_NAME_MES = "MesPU";
	@PersistenceContext(unitName = UNIT_NAME_MES)
	private EntityManager emMes;

	public SuClassDAO() {
		super(SuClass.class);
	}

	public SuClass getById(Integer id) {
		SuClass result = new SuClass();
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("id", id);
		String namedQuery = "SELECT sc FROM SuClass sc WHERE sc.id = :id";
		try {
			Query query = emMes.createQuery(namedQuery);
			if (parameters != null && !parameters.isEmpty()) {
				populateQueryParameters(query, parameters);
			}
			query.setMaxResults(1);
			result = (SuClass) query.getResultList().get(0);
		} catch (Exception e) {
			@SuppressWarnings("unused")
			Log log = new Log("ERROR SuClassDAO.getById: " + e.getMessage());
		}
		if (result == null) {
			return new SuClass();
		}
		return result;
	}
	
	@SuppressWarnings({ "unchecked" })
	public List<SuClass> getAll() {
		List<SuClass> result = new ArrayList<SuClass>();
		String namedQuery = "SELECT sc FROM SuClass sc WHERE sc.isActive = 1";
		try {
			Query query = emMes.createQuery(namedQuery);
			result = (ArrayList<SuClass>) query.getResultList();
		} catch (Exception e) {
			@SuppressWarnings("unused")
			Log log = new Log("ERROR SuClassDAO.findAllMes: " + e.getMessage());
		}
		return result;
	}
	

	public SuClass getByMesId(Integer mesId) {
		SuClass emptyResult = new SuClass();
		SuClass result = new SuClass();
		try {
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("mesId", mesId);
			String namedQuery = "SELECT sc FROM SuClass sc WHERE sc.mesId = :mesId";
			Query query = emMes.createQuery(namedQuery);
			if (parameters != null && !parameters.isEmpty()) {
				populateQueryParameters(query, parameters);
			}
			query.setMaxResults(1);
			result = (SuClass) query.getResultList().get(0);
		} catch (Exception e) {
			System.out.println("ERROR SuClassDAO.getByMesId: "
					+ e.getMessage());
		}
		if (result == null) {
			return emptyResult;
		}
		return result;
	}
}