package com.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.model.EKATTE;
import com.model.Log;

@Stateless
public class EKATTEDAO extends GenericDAO<EKATTE> {

	private final static String UNIT_NAME_MES = "MesPU";
	@PersistenceContext(unitName = UNIT_NAME_MES)
	private EntityManager emMes;

	public EKATTEDAO() {
		super(EKATTE.class);
	}
	
	public EKATTE getById(Integer id) {
		EKATTE result = new EKATTE();
		try {
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("id", id);
			String namedQuery = "SELECT e FROM EKATTE e WHERE e.id = :id";
			Query query = emMes.createQuery(namedQuery);
			if (parameters != null && !parameters.isEmpty()) {
				populateQueryParameters(query, parameters);
			}
			result = (EKATTE) query.getSingleResult();
		} catch (Exception e) {
			@SuppressWarnings("unused")
			Log log = new Log("ERROR EKATTEDAO.getById: " + e.getMessage());
		}
		if (result == null) {
			return new EKATTE();
		}
		return result;
	}
	
	@SuppressWarnings({ "unchecked" })
	public List<EKATTE> getAllSortByMesName() {
		List<EKATTE> ekattes = new ArrayList<EKATTE>();
		try {
			String namedQuery = "SELECT ekatte FROM EKATTE ekatte ORDER BY ekatte.mesName ASC";
			Query query = emMes.createQuery(namedQuery);
			ekattes = (ArrayList<EKATTE>) query.getResultList();
		} catch (Exception e) {
			@SuppressWarnings("unused")
			Log log = new Log("ERROR EKATTEDAO.getAllSortByMesName: " + e.getMessage());
		}
		return ekattes;
	}
	
	@SuppressWarnings({ "unchecked" })
	public List<EKATTE> getAllSortByMunicipality() {
		List<EKATTE> ekattes = new ArrayList<EKATTE>();
		try {
			String namedQuery = "SELECT ekatte FROM EKATTE ekatte WHERE ekatte.isActive = 1 ORDER BY ekatte.municipality, ekatte.mesName, ekatte.region ASC";
			Query query = emMes.createQuery(namedQuery);
			ekattes = (ArrayList<EKATTE>) query.getResultList();
		} catch (Exception e) {
			@SuppressWarnings("unused")
			Log log = new Log("ERROR EKATTEDAO.getAllSortByMunicipality: " + e.getMessage());
		}
		return ekattes;
	}
}