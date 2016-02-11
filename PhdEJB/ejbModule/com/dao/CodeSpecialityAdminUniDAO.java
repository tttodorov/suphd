package com.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.model.CodeSpecialityAdminUni;
import com.model.Log;

@Stateless
public class CodeSpecialityAdminUniDAO extends GenericDAO<CodeSpecialityAdminUni> {

	private final static String UNIT_NAME_MES = "MesPU";
	@PersistenceContext(unitName = UNIT_NAME_MES)
	private EntityManager emMes;

	public CodeSpecialityAdminUniDAO() {
		super(CodeSpecialityAdminUni.class);
	}

	public CodeSpecialityAdminUni getById(Integer id) {
		CodeSpecialityAdminUni result = new CodeSpecialityAdminUni();
		try {
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("id", id);
			String namedQuery = "SELECT s FROM CodeSpecialityAdminUni s WHERE s.id = :id";
			Query query = emMes.createQuery(namedQuery);
			if (parameters != null && !parameters.isEmpty()) {
				populateQueryParameters(query, parameters);
			}
			result = (CodeSpecialityAdminUni) query.getSingleResult();
		} catch (Exception e) {
			@SuppressWarnings("unused")
			Log log = new Log("ERROR CodeSpecialityAdminUniDAO.getById: " + e.getMessage());
		}
		if (result == null) {
			return new CodeSpecialityAdminUni();
		}
		return result;
	}

	@SuppressWarnings({ "unchecked" })
	public List<CodeSpecialityAdminUni> getAll() {
		List<CodeSpecialityAdminUni> codeNSIs = new ArrayList<CodeSpecialityAdminUni>();
		try {
			String namedQuery = "SELECT s FROM CodeSpecialityAdminUni s WHERE s.isActive = 1";
			Query query = emMes.createQuery(namedQuery);
			codeNSIs = (ArrayList<CodeSpecialityAdminUni>) query.getResultList();
		} catch (Exception e) {
			System.out.println("ERROR CodeSpecialityAdminUniDAO: " + e.getMessage());
		}
		return codeNSIs;
	}
}