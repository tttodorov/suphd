package com.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.model.EducationReasonForAcc;
import com.model.Log;

@Stateless
public class EducationReasonForAccDAO extends GenericDAO<EducationReasonForAcc> {

	private final static String UNIT_NAME_MES = "MesPU";
	@PersistenceContext(unitName = UNIT_NAME_MES)
	private EntityManager emMes;

	public EducationReasonForAccDAO() {
		super(EducationReasonForAcc.class);
	}

	public EducationReasonForAcc getById(Integer id) {
		EducationReasonForAcc result = new EducationReasonForAcc();
		try {
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("id", id);
			String namedQuery = "SELECT erac FROM EducationReasonForAcc erac WHERE erac.id = :id";
			Query query = emMes.createQuery(namedQuery);
			if (parameters != null && !parameters.isEmpty()) {
				populateQueryParameters(query, parameters);
			}
			query.setMaxResults(1);
			result = (EducationReasonForAcc) query.getResultList().get(0);
		} catch (Exception e) {
			@SuppressWarnings("unused")
			Log log = new Log("ERROR EducationReasonForAccDAO.getById: " + e.getMessage());
		}
		if (result == null) {
			return new EducationReasonForAcc();
		}
		return result;
	}
	
	@SuppressWarnings({ "unchecked" })
	public List<EducationReasonForAcc> getAll() {
		List<EducationReasonForAcc> result = new ArrayList<EducationReasonForAcc>();
		try {
			String namedQuery = "SELECT erac FROM EducationReasonForAcc erac WHERE erac.isActive = 1";
			Query query = emMes.createQuery(namedQuery);
			result = (ArrayList<EducationReasonForAcc>) query.getResultList();
		} catch (Exception e) {
			@SuppressWarnings("unused")
			Log log = new Log("ERROR EducationReasonForAccDAO.getAll: " + e.getMessage());
		}
		return result;
	}
	
	public EducationReasonForAcc getByMesId(Integer mesId) {
		EducationReasonForAcc emptyResult = new EducationReasonForAcc();
		EducationReasonForAcc result = new EducationReasonForAcc();
		try {
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("mesId", mesId);
			String namedQuery = "SELECT erac FROM EducationReasonForAcc erac WHERE erac.mesId = :mesId";
			Query query = emMes.createQuery(namedQuery);
			if (parameters != null && !parameters.isEmpty()) {
				populateQueryParameters(query, parameters);
			}
			query.setMaxResults(1);
			result = (EducationReasonForAcc) query.getResultList().get(0);
		} catch (Exception e) {
			System.out.println("ERROR EducationReasonForAccDAO.getByMesId: "
					+ e.getMessage());
		}
		if (result == null) {
			return emptyResult;
		}
		return result;
	}
}