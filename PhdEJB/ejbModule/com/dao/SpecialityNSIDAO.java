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
import com.model.SpecialityNSI;

@Stateless
public class SpecialityNSIDAO extends GenericDAO<SpecialityNSI> {

	private final static String UNIT_NAME_MES = "MesPU";
	@PersistenceContext(unitName = UNIT_NAME_MES)
	private EntityManager emMes;

	public SpecialityNSIDAO() {
		super(SpecialityNSI.class);
	}

	public SpecialityNSI getById(Integer id) {
		SpecialityNSI result = new SpecialityNSI();
		try {
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("id", id);
			String namedQuery = "SELECT s FROM SpecialityNSI s WHERE s.id = :id";
			Query query = emMes.createQuery(namedQuery);
			if (parameters != null && !parameters.isEmpty()) {
				populateQueryParameters(query, parameters);
			}
			result = (SpecialityNSI) query.getSingleResult();
		} catch (Exception e) {
			@SuppressWarnings("unused")
			Log log = new Log("ERROR SpecialityDAO.getById: " + e.getMessage());
		}
		if (result == null) {
			return new SpecialityNSI();
		}
		return result;
	}

	@SuppressWarnings({ "unchecked" })
	public List<SpecialityNSI> getAll() {
		List<SpecialityNSI> result = new ArrayList<SpecialityNSI>();
		try {
			String namedQuery = "SELECT s FROM SpecialityNSI s WHERE s.isActive = 1";
			Query query = emMes.createQuery(namedQuery);
			result = (ArrayList<SpecialityNSI>) query.getResultList();
		} catch (Exception e) {
			@SuppressWarnings("unused")
			Log log = new Log("ERROR SpecialityDAO.getAll: " + e.getMessage());
		}
		return result;
	}
}