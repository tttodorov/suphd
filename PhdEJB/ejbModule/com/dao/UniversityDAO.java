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
import com.model.University;

@Stateless
public class UniversityDAO extends GenericDAO<University> {

	private final static String UNIT_NAME_MES = "MesPU";
	@PersistenceContext(unitName = UNIT_NAME_MES)
	private EntityManager emMes;

	public UniversityDAO() {
		super(University.class);
	}

	public University getById(Integer id) {
		University result = new University();
		try {
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("id", id);
			String namedQuery = "SELECT u FROM University u WHERE u.id = :id";
			Query query = emMes.createQuery(namedQuery);
			if (parameters != null && !parameters.isEmpty()) {
				populateQueryParameters(query, parameters);
			}
			result = (University) query.getSingleResult();
		} catch (Exception e) {
			@SuppressWarnings("unused")
			Log log = new Log("ERROR UniversityDAO.getById: " + e.getMessage());
		}
		if (result == null) {
			return new University();
		}
		return result;
	}

	@SuppressWarnings({ "unchecked" })
	public List<University> getAll() {
		List<University> codeUniversities = new ArrayList<University>();
		try {
			String namedQuery = "SELECT university FROM University university ORDER BY university.id ASC";
			Query query = emMes.createQuery(namedQuery);
			codeUniversities = (ArrayList<University>) query.getResultList();
		} catch (Exception e) {
			System.out.println("ERROR UniversityDAO.findAllMes: " + e.getMessage());
		}
		return codeUniversities;
	}

	public University getByMesId(Integer universityId) {
		University emptyResult = new University();
		University result = new University();
		try {
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("universityId", universityId);
			String namedQuery = "SELECT university FROM University university WHERE university.id = :universityId";
			Query query = emMes.createQuery(namedQuery);
			if (parameters != null && !parameters.isEmpty()) {
				populateQueryParameters(query, parameters);
			}
			query.setMaxResults(1);
			result = (University) query.getResultList().get(0);
		} catch (Exception e) {
			System.out.println("ERROR UniversityDAO.getByUniversityId: "
					+ e.getMessage());
		}
		if (result == null) {
			return emptyResult;
		}
		return result;
	}
}