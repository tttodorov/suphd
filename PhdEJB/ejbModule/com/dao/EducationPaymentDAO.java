package com.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.model.EducationPayment;
import com.model.Log;

@Stateless
public class EducationPaymentDAO extends GenericDAO<EducationPayment> {

	private final static String UNIT_NAME_MES = "MesPU";
	@PersistenceContext(unitName = UNIT_NAME_MES)
	private EntityManager emMes;

	public EducationPaymentDAO() {
		super(EducationPayment.class);
	}

	public EducationPayment getById(Integer id) {
		EducationPayment result = new EducationPayment();
		try {
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("id", id);
			String namedQuery = "SELECT ep FROM EducationPayment ep WHERE ep.id = :id";
			Query query = emMes.createQuery(namedQuery);
			if (parameters != null && !parameters.isEmpty()) {
				populateQueryParameters(query, parameters);
			}
			query.setMaxResults(1);
			result = (EducationPayment) query.getResultList().get(0);
		} catch (Exception e) {
			@SuppressWarnings("unused")
			Log log = new Log("ERROR EducationPaymentDAO.getById: " + e.getMessage());
		}
		if (result == null) {
			return new EducationPayment();
		}
		return result;
	}
	
	@SuppressWarnings({ "unchecked" })
	public List<EducationPayment> getAll() {
		List<EducationPayment> result = new ArrayList<EducationPayment>();
		try {
			String namedQuery = "SELECT ep FROM EducationPayment ep WHERE ep.isActive = 1";
			Query query = emMes.createQuery(namedQuery);
			result = (ArrayList<EducationPayment>) query.getResultList();
		} catch (Exception e) {
			@SuppressWarnings("unused")
			Log log = new Log("ERROR EducationPaymentDAO.getAll: " + e.getMessage());
		}
		return result;
	}
}