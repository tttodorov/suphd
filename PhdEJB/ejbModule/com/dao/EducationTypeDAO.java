package com.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.model.EducationType;
import com.model.Log;

@Stateless
public class EducationTypeDAO extends GenericDAO<EducationType> {

	private final static String UNIT_NAME_MES = "MesPU";
	@PersistenceContext(unitName = UNIT_NAME_MES)
	private EntityManager emMes;

	public EducationTypeDAO() {
		super(EducationType.class);
	}

	public EducationType getById(Integer id) {
		EducationType result = new EducationType();
		try {
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("id", id);
			String namedQuery = "SELECT et FROM EducationType et WHERE et.id = :id";
			Query query = emMes.createQuery(namedQuery);
			if (parameters != null && !parameters.isEmpty()) {
				populateQueryParameters(query, parameters);
			}
			query.setMaxResults(1);
			result = (EducationType) query.getResultList().get(0);
		} catch (Exception e) {
			@SuppressWarnings("unused")
			Log log = new Log("ERROR EducationTypeDAO.getById: " + e.getMessage());
		}
		if (result == null) {
			return new EducationType();
		}
		return result;
	}
	
	@SuppressWarnings({ "unchecked" })
	public List<EducationType> getAll() {
		List<EducationType> result = new ArrayList<EducationType>();
		try {
			String namedQuery = "SELECT et FROM EducationType et WHERE et.isActive = 1";
			Query query = emMes.createQuery(namedQuery);
			result = (ArrayList<EducationType>) query.getResultList();
		} catch (Exception e) {
			@SuppressWarnings("unused")
			Log log = new Log("ERROR EducationTypeDAO.getAll: " + e.getMessage());
		}
		return result;
	}
}