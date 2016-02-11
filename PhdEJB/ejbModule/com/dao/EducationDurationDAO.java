package com.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.model.EducationDuration;
import com.model.Log;

@Stateless
public class EducationDurationDAO extends GenericDAO<EducationDuration> {

	private final static String UNIT_NAME_MES = "MesPU";
	@PersistenceContext(unitName = UNIT_NAME_MES)
	private EntityManager emMes;

	public EducationDurationDAO() {
		super(EducationDuration.class);
	}

	public EducationDuration getById(Integer id) {
		EducationDuration result = new EducationDuration();
		try {
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("id", id);
			String namedQuery = "SELECT ed FROM EducationDuration ed WHERE ed.id = :id";
			Query query = emMes.createQuery(namedQuery);
			if (parameters != null && !parameters.isEmpty()) {
				populateQueryParameters(query, parameters);
			}
			query.setMaxResults(1);
			result = (EducationDuration) query.getResultList().get(0);
		} catch (Exception e) {
			@SuppressWarnings("unused")
			Log log = new Log("ERROR EducationDurationDAO.getById: " + e.getMessage());
		}
		if (result == null) {
			return new EducationDuration();
		}
		return result;
	}
	
	@SuppressWarnings({ "unchecked" })
	public List<EducationDuration> getAll() {
		List<EducationDuration> result = new ArrayList<EducationDuration>();
		try {
			String namedQuery = "SELECT ed FROM EducationDuration ed WHERE ed.isActive = 1";
			Query query = emMes.createQuery(namedQuery);
			result = (ArrayList<EducationDuration>) query.getResultList();
		} catch (Exception e) {
			@SuppressWarnings("unused")
			Log log = new Log("ERROR EducationDurationDAO.getAll: " + e.getMessage());
		}
		return result;
	}
}