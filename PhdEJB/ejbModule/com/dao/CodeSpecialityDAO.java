package com.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.model.CodeSpeciality;
import com.model.Log;

@Stateless
public class CodeSpecialityDAO extends GenericDAO<CodeSpeciality> {

	private final static String UNIT_NAME_MES = "MesPU";
	@PersistenceContext(unitName = UNIT_NAME_MES)
	private EntityManager emMes;

	public CodeSpecialityDAO() {
		super(CodeSpeciality.class);
	}

	public CodeSpeciality getById(Integer id) {
		CodeSpeciality result = new CodeSpeciality();
		try {
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("id", id);
			String namedQuery = "SELECT s FROM CodeSpeciality s WHERE s.id = :id";
			Query query = emMes.createQuery(namedQuery);
			if (parameters != null && !parameters.isEmpty()) {
				populateQueryParameters(query, parameters);
			}
			result = (CodeSpeciality) query.getSingleResult();
		} catch (Exception e) {
			@SuppressWarnings("unused")
			Log log = new Log("ERROR CodeSpecialityDAO.getById: " + e.getMessage());
		}
		if (result == null) {
			return new CodeSpeciality();
		}
		return result;
	}

	@SuppressWarnings({ "unchecked" })
	public List<CodeSpeciality> getAll() {
		List<CodeSpeciality> codeNSIs = new ArrayList<CodeSpeciality>();
		try {
			String namedQuery = "SELECT s FROM CodeSpeciality s WHERE s.isActive = 1";
			Query query = emMes.createQuery(namedQuery);
			codeNSIs = (ArrayList<CodeSpeciality>) query.getResultList();
		} catch (Exception e) {
			System.out.println("ERROR CodeSpecialityDAO: " + e.getMessage());
		}
		return codeNSIs;
	}
}