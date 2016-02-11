package com.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.model.Faculty;
import com.model.Log;

@Stateless
public class FacultyDAO extends GenericDAO<Faculty> {

	private final static String UNIT_NAME_MES = "MesPU";
	@PersistenceContext(unitName = UNIT_NAME_MES)
	private EntityManager emMes;

	public FacultyDAO() {
		super(Faculty.class);
	}

	public Faculty getById(Integer id) {
		Faculty result = new Faculty();
		try {
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("id", id);
			String namedQuery = "SELECT f FROM Faculty f WHERE f.id = :id";
			Query query = emMes.createQuery(namedQuery);
			if (parameters != null && !parameters.isEmpty()) {
				populateQueryParameters(query, parameters);
			}
			result = (Faculty) query.getSingleResult();
		} catch (Exception e) {
			@SuppressWarnings("unused")
			Log log = new Log("ERROR FacultyDAO.getById: " + e.getMessage());
		}
		if (result == null) {
			return new Faculty();
		}
		return result;
	}
	
	@SuppressWarnings({ "unchecked" })
	public List<Faculty> getAll() {
		List<Faculty> faculties = new ArrayList<Faculty>();
		try {
			String namedQuery = "SELECT f FROM Faculty f WHERE f.isActive = 1";
			Query query = emMes.createQuery(namedQuery);
			faculties = (ArrayList<Faculty>) query.getResultList();
		} catch (Exception e) {
			@SuppressWarnings("unused")
			Log log = new Log("ERROR FacultyDAO: " + e.getMessage());
		}
		return faculties;
	}
	
	@SuppressWarnings({ "unchecked" })
	public List<Faculty> getAllFacultiesReportMes() {
		List<Faculty> faculties = new ArrayList<Faculty>();
		try {
			String namedQuery = "SELECT f FROM Faculty f WHERE f.isActive = 1 ORDER BY f.mesId ASC";
			Query query = emMes.createQuery(namedQuery);
			faculties = (ArrayList<Faculty>) query.getResultList();
		} catch (Exception e) {
			@SuppressWarnings("unused")
			Log log = new Log("ERROR FacultyDAO: " + e.getMessage());
		}
		return faculties;
	}
}