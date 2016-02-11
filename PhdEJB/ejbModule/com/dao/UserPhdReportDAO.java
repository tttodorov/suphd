package com.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.model.PhdReport;

@Stateless
public class UserPhdReportDAO extends GenericDAO<PhdReport> {

	private final static String UNIT_NAME = "PhdPU";

	@PersistenceContext(unitName = UNIT_NAME)
	private EntityManager em;

	public UserPhdReportDAO() {
		super(PhdReport.class);
	}

	public void delete(PhdReport phdReport) {
		super.delete(phdReport.getId(), PhdReport.class);
	}

	@SuppressWarnings("unchecked")
	public ArrayList<PhdReport> getAllUserPhdReportByYear(Integer year) {
		ArrayList<PhdReport> result = new ArrayList<PhdReport>();
		try {
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("year", year);
			String namedQuery = "SELECT report FROM PhdReport report WHERE report.year = :year ORDER BY report.month ASC";
			Query query = em.createQuery(namedQuery);
			if (parameters != null && !parameters.isEmpty()) {
				populateQueryParameters(query, parameters);
			}
			result = (ArrayList<PhdReport>) query.getResultList();
		} catch (Exception e) {
			System.out.println("ERROR UserPhdReportDAO: " + e.getMessage());
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<PhdReport> getAllUserPhdReportByUserYear(
			Integer userId, Integer year) {
		ArrayList<PhdReport> result = new ArrayList<PhdReport>();
		try {
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("year", year);
			parameters.put("userId", userId);
			String namedQuery = "SELECT report FROM PhdReport report WHERE report.year = :year AND report.userId = :userId ORDER BY report.month ASC";
			Query query = em.createQuery(namedQuery);
			if (parameters != null && !parameters.isEmpty()) {
				populateQueryParameters(query, parameters);
			}
			result = (ArrayList<PhdReport>) query.getResultList();
		} catch (Exception e) {
			System.out.println("ERROR UserPhdReportDAO: " + e.getMessage());
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Integer> getAllUserPhdReportYears(Integer userId) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		try {
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("userId", userId);
			String namedQuery = "select DISTINCT(report.year) from PhdReport report WHERE report.userId = :userId ORDER BY report.year ASC";
			Query query = em.createQuery(namedQuery);
			if (parameters != null && !parameters.isEmpty()) {
				populateQueryParameters(query, parameters);
			}
			result = (ArrayList<Integer>) query.getResultList();
		} catch (Exception e) {
			System.out.println("UserPhdReportDAO: " + e.getMessage());
		}
		return result;
	}

	public PhdReport findReportById(Integer id) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("id", id);
		String query = "SELECT report FROM PhdReport report WHERE report.id = :id";
		return super.findOneResult(query, parameters);
	}

	public PhdReport findReportByYearMonthUser(Integer year, Integer month,
			Integer userId) {
		PhdReport emptyResult = new PhdReport();
		PhdReport result = new PhdReport();
		try {
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("year", year);
			parameters.put("month", month);
			parameters.put("userId", userId);
			String query = "SELECT report FROM PhdReport report WHERE report.year = :year AND report.month = :month AND report.userId = :userId";
			result = (PhdReport) super.findOneResult(query, parameters);
		} catch (Exception e) {
			System.out.println("UserPhdReportDAO: " + e.getMessage());
		}
		if (result == null) {
			return emptyResult;
		}
		return result;
	}

	public PhdReport getlast() {
		PhdReport emptyResult = new PhdReport();
		PhdReport result = new PhdReport();
		try {
			String namedQuery = "SELECT report FROM PhdReport report ORDER BY report.id DESC";
			Query query = em.createQuery(namedQuery);
			query.setMaxResults(1);
			result = (PhdReport) query.getResultList().get(0);
		} catch (Exception e) {
			System.out.println("UserPhdReportDAO: " + e.getMessage());
		}
		if (result == null) {
			return emptyResult;
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<PhdReport> findReportByUser(Integer userId) {
		ArrayList<PhdReport> result = new ArrayList<PhdReport>();
		try {
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("userId", userId);
			String namedQuery = "SELECT report FROM PhdReport report WHERE report.userId = :userId";
			Query query = em.createQuery(namedQuery);
			if (parameters != null && !parameters.isEmpty()) {
				populateQueryParameters(query, parameters);
			}
			result = (ArrayList<PhdReport>) query.getResultList();
		} catch (Exception e) {
			System.out.println("UserPhdReportDAO: " + e.getMessage());
		}
		return result;
	}
}