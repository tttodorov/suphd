package com.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.model.Speciality;
import com.model.Log;

@Stateless
public class SpecialityDAO extends GenericDAO<Speciality> {

	private final static String UNIT_NAME_MES = "MesPU";
	@PersistenceContext(unitName = UNIT_NAME_MES)
	private EntityManager emMes;

	public SpecialityDAO() {
		super(Speciality.class);
	}

	public Speciality getById(Integer id) {
		Speciality result = new Speciality();
		try {
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("id", id);
			String namedQuery = "SELECT s FROM Speciality s WHERE s.id = :id";
			Query query = emMes.createQuery(namedQuery);
			if (parameters != null && !parameters.isEmpty()) {
				populateQueryParameters(query, parameters);
			}
			result = (Speciality) query.getSingleResult();
		} catch (Exception e) {
			@SuppressWarnings("unused")
			Log log = new Log("ERROR SpecialityDAO.getById: " + e.getMessage());
		}
		if (result == null) {
			return new Speciality();
		}
		return result;
	}

	@SuppressWarnings({ "unchecked" })
	public List<Speciality> getAll() {
		List<Speciality> result = new ArrayList<Speciality>();
		try {
			String namedQuery = "SELECT s FROM Speciality s WHERE s.isActive = 1";
			Query query = emMes.createQuery(namedQuery);
			result = (ArrayList<Speciality>) query.getResultList();
		} catch (Exception e) {
			System.out.println("ERROR SpecialityDAO: " + e.getMessage());
		}
		return result;
	}

	@SuppressWarnings({ "unchecked" })
	public List<Speciality> getAllReportMes() {
		List<Speciality> result = new ArrayList<Speciality>();
		try {
			String namedQuery = "SELECT s FROM Speciality s WHERE s.isActive = 1 ORDER BY s.faculty, s.mesSpecialityId, s.mesEdForm ASC";
			Query query = emMes.createQuery(namedQuery);
			result = (ArrayList<Speciality>) query.getResultList();
		} catch (Exception e) {
			System.out.println("ERROR SpecialityDAO: " + e.getMessage());
		}
		return result;
	}
}