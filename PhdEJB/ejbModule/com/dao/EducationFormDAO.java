package com.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.model.EducationForm;
import com.model.Log;

@Stateless
public class EducationFormDAO extends GenericDAO<EducationForm> {

	private final static String UNIT_NAME_MES = "MesPU";
	@PersistenceContext(unitName = UNIT_NAME_MES)
	private EntityManager emMes;

	public EducationFormDAO() {
		super(EducationForm.class);
	}

	public EducationForm getById(Integer id) {
		EducationForm result = new EducationForm();
		try {
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("id", id);
			String namedQuery = "SELECT ef FROM EducationForm ef WHERE ef.id = :id";
			Query query = emMes.createQuery(namedQuery);
			if (parameters != null && !parameters.isEmpty()) {
				populateQueryParameters(query, parameters);
			}
			query.setMaxResults(1);
			result = (EducationForm) query.getResultList().get(0);
		} catch (Exception e) {
			@SuppressWarnings("unused")
			Log log = new Log("ERROR EducationFormDAO.getById: " + e.getMessage());
		}
		if (result == null) {
			return new EducationForm();
		}
		return result;
	}
	
	@SuppressWarnings({ "unchecked" })
	public List<EducationForm> getAll() {
		List<EducationForm> result = new ArrayList<EducationForm>();
		try {
			String namedQuery = "SELECT ef FROM EducationForm ef WHERE ef.isActive = 1";
			Query query = emMes.createQuery(namedQuery);
			result = (ArrayList<EducationForm>) query.getResultList();
		} catch (Exception e) {
			@SuppressWarnings("unused")
			Log log = new Log("ERROR EducationFormDAO.getAll: " + e.getMessage());
		}
		return result;
	}
}