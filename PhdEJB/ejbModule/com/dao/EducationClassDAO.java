package com.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.model.EducationClass;
import com.model.Log;

@Stateless
public class EducationClassDAO extends GenericDAO<EducationClass> {

	private final static String UNIT_NAME_MES = "MesPU";
	@PersistenceContext(unitName = UNIT_NAME_MES)
	private EntityManager emMes;

	public EducationClassDAO() {
		super(EducationClass.class);
	}

	public EducationClass getById(Integer id) {
		EducationClass result = new EducationClass();
		try {
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("id", id);
			String namedQuery = "SELECT ec FROM EducationClass ec WHERE ec.id = :id";
			Query query = emMes.createQuery(namedQuery);
			if (parameters != null && !parameters.isEmpty()) {
				populateQueryParameters(query, parameters);
			}
			query.setMaxResults(1);
			result = (EducationClass) query.getResultList().get(0);
		} catch (Exception e) {
			@SuppressWarnings("unused")
			Log log = new Log("ERROR EducationClassDAO.getById: " + e.getMessage());
		}
		if (result == null) {
			return new EducationClass();
		}
		return result;
	}
	
	@SuppressWarnings({ "unchecked" })
	public List<EducationClass> getAll() {
		List<EducationClass> result = new ArrayList<EducationClass>();
		try {
			String namedQuery = "SELECT ec FROM EducationClass ec WHERE ec.isActive = 1";
			Query query = emMes.createQuery(namedQuery);
			result = (ArrayList<EducationClass>) query.getResultList();
		} catch (Exception e) {
			@SuppressWarnings("unused")
			Log log = new Log("ERROR EducationClassDAO.getAll: " + e.getMessage());
		}
		return result;
	}
}