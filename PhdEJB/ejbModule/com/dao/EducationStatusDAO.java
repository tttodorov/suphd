package com.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.model.EducationStatus;
import com.model.Log;

@Stateless
public class EducationStatusDAO extends GenericDAO<EducationStatus> {

	private final static String UNIT_NAME_MES = "MesPU";
	@PersistenceContext(unitName = UNIT_NAME_MES)
	private EntityManager emMes;

	public EducationStatusDAO() {
		super(EducationStatus.class);
	}

	public EducationStatus getById(Integer id) {
		EducationStatus result = new EducationStatus();
		try {
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("id", id);
			String namedQuery = "SELECT es FROM EducationStatus es WHERE es.id = :id";
			Query query = emMes.createQuery(namedQuery);
			if (parameters != null && !parameters.isEmpty()) {
				populateQueryParameters(query, parameters);
			}
			query.setMaxResults(1);
			result = (EducationStatus) query.getResultList().get(0);
		} catch (Exception e) {
			@SuppressWarnings("unused")
			Log log = new Log("ERROR EducationStatusDAO.getById: " + e.getMessage());
		}
		if (result == null) {
			return new EducationStatus();
		}
		return result;
	}
	
	@SuppressWarnings({ "unchecked" })
	public List<EducationStatus> getAll() {
		List<EducationStatus> result = new ArrayList<EducationStatus>();
		try {
			String namedQuery = "SELECT es FROM EducationStatus es WHERE es.isActive = 1";
			Query query = emMes.createQuery(namedQuery);
			result = (ArrayList<EducationStatus>) query.getResultList();
		} catch (Exception e) {
			@SuppressWarnings("unused")
			Log log = new Log("ERROR EducationStatusDAO.getAll: " + e.getMessage());
		}
		return result;
	}
}