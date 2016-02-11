package com.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.model.Log;
import com.model.PhdStatusChange;

@Stateless
public class UserPhdStatusChangeDAO extends GenericDAO<PhdStatusChange> {

	private final static String UNIT_NAME = "PhdPU";
	
	@PersistenceContext(unitName = UNIT_NAME)
	private EntityManager em;
	
	public UserPhdStatusChangeDAO() {
		super(PhdStatusChange.class);
	}

	public PhdStatusChange getById(Integer id) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("id", id);
		String query = "SELECT upsc FROM PhdStatusChange upsc WHERE upsc.id = :id";
		return super.findOneResult(query, parameters);
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<PhdStatusChange> getAllByUser(Integer userId) {
		ArrayList<PhdStatusChange> result = new ArrayList<PhdStatusChange>();
		try {
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("userId", userId);
			String namedQuery = "SELECT upsc FROM PhdStatusChange upsc WHERE upsc.userId = :userId ORDER BY upsc.id ASC";
			Query query = em.createQuery(namedQuery);
			if (parameters != null && !parameters.isEmpty()) {
				populateQueryParameters(query, parameters);
			}
			result = (ArrayList<PhdStatusChange>) query.getResultList();
		} catch (Exception e) {
			@SuppressWarnings("unused")
			Log log = new Log("ERROR UserPhdStatusChangeDAO.getLastByUser: " + e.getMessage());
		}
		return result;
	}
	
	public PhdStatusChange getLastByUser(Integer userId) {
		PhdStatusChange result = new PhdStatusChange();
		try {
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("userId", userId);
			String namedQuery = "SELECT upsc FROM PhdStatusChange upsc WHERE upsc.userId = :userId ORDER BY upsc.id DESC";
			Query query = em.createQuery(namedQuery);
			if (parameters != null && !parameters.isEmpty()) {
				populateQueryParameters(query, parameters);
			}
			result = (PhdStatusChange) query.getResultList().get(0);
		} catch (Exception e) {
			@SuppressWarnings("unused")
			Log log = new Log("ERROR UserPhdStatusChangeDAO.getLastByUser: " + e.getMessage());
		}
		return result;
	}
}