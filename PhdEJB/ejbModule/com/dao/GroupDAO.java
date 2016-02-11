package com.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.model.Group;
import com.model.Log;

@Stateless
public class GroupDAO extends GenericDAO<Group> {

	private final static String UNIT_NAME_MES = "MesPU";
	@PersistenceContext(unitName = UNIT_NAME_MES)
	private EntityManager emMes;

	public GroupDAO() {
		super(Group.class);
	}

	public Group getById(Integer id) {
		Group result = new Group();
		try {
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("id", id);
			String namedQuery = "SELECT g FROM Group g WHERE g.id = :id";
			Query query = emMes.createQuery(namedQuery);
			if (parameters != null && !parameters.isEmpty()) {
				populateQueryParameters(query, parameters);
			}
			result = (Group) query.getSingleResult();
		} catch (Exception e) {
			@SuppressWarnings("unused")
			Log log = new Log("ERROR GroupDAO.getById: " + e.getMessage());
		}
		if (result == null) {
			return new Group();
		}
		return result;
	}

	@SuppressWarnings({ "unchecked" })
	public List<Group> getAll() {
		List<Group> groups = new ArrayList<Group>();
		try {
			String namedQuery = "SELECT g FROM Group g WHERE g.isActive = 1";
			Query query = emMes.createQuery(namedQuery);
			groups = (ArrayList<Group>) query.getResultList();
		} catch (Exception e) {
			@SuppressWarnings("unused")
			Log log = new Log("ERROR GroupDAO: " + e.getMessage());
		}
		return groups;
	}
}