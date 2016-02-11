package com.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.model.EducationProfGroup;
import com.model.Log;

@Stateless
public class EducationProfGroupDAO extends GenericDAO<EducationProfGroup> {

	private final static String UNIT_NAME_MES = "MesPU";
	@PersistenceContext(unitName = UNIT_NAME_MES)
	private EntityManager emMes;

	public EducationProfGroupDAO() {
		super(EducationProfGroup.class);
	}

	public EducationProfGroup getById(Integer id) {
		EducationProfGroup result = new EducationProfGroup();
		try {
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("id", id);
			String namedQuery = "SELECT epg FROM EducationProfGroup epg WHERE epg.id = :id";
			Query query = emMes.createQuery(namedQuery);
			if (parameters != null && !parameters.isEmpty()) {
				populateQueryParameters(query, parameters);
			}
			query.setMaxResults(1);
			result = (EducationProfGroup) query.getResultList().get(0);
		} catch (Exception e) {
			@SuppressWarnings("unused")
			Log log = new Log("ERROR EducationProfGroupDAO.getById: " + e.getMessage());
		}
		if (result == null) {
			return new EducationProfGroup();
		}
		return result;
	}
	
	@SuppressWarnings({ "unchecked" })
	public List<EducationProfGroup> getAll() {
		List<EducationProfGroup> result = new ArrayList<EducationProfGroup>();
		try {
			String namedQuery = "SELECT epg FROM EducationProfGroup epg WHERE epg.isActive = 1";
			Query query = emMes.createQuery(namedQuery);
			result = (ArrayList<EducationProfGroup>) query.getResultList();
		} catch (Exception e) {
			@SuppressWarnings("unused")
			Log log = new Log("ERROR EducationProfGroupDAO.getAll: " + e.getMessage());
		}
		return result;
	}
}