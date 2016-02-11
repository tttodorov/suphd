package com.facade;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.dao.UserPhdReportDAO;
import com.model.PhdReport;

@Stateless
public class UserPhdReportFacadeImp implements UserPhdReportFacade {

	@EJB
	private UserPhdReportDAO userPhdReportDAO;

	@Override
	public void save(PhdReport phdReport) {
		userPhdReportDAO.save(phdReport);
	}

	@Override
	public PhdReport update(PhdReport phdReport) {
		return userPhdReportDAO.update(phdReport);
	}

	@Override
	public void delete(PhdReport phdReport) {
		userPhdReportDAO.delete(phdReport);
	}

	@Override
	public PhdReport find(int entityID) {
		return userPhdReportDAO.find(entityID);
	}

	@Override
	public List<PhdReport> findAll() {
		return userPhdReportDAO.findAll();
	}

	@Override
	public ArrayList<Integer> getAllUserPhdReportYears(Integer userId) {
		return userPhdReportDAO.getAllUserPhdReportYears(userId);
	}

	@Override
	public ArrayList<PhdReport> getAllUserPhdReportByYear(Integer year) {
		return userPhdReportDAO.getAllUserPhdReportByYear(year);
	}

	@Override
	public ArrayList<PhdReport> getAllUserPhdReportByUserYear(
			Integer userId, Integer year) {
		return userPhdReportDAO.getAllUserPhdReportByUserYear(userId, year);
	}

	@Override
	public PhdReport findReportById(Integer id) {
		return userPhdReportDAO.findReportById(id);
	}

	@Override
	public PhdReport findReportByYearMonthUser(Integer year, Integer month,
			Integer userId) {
		System.err.println("UserPhdReportFacadeImp.findReportByYearMonthUser ");
		return userPhdReportDAO.findReportByYearMonthUser(year, month, userId);
	}

	@Override
	public PhdReport getLast() {
		return userPhdReportDAO.getlast();
	}

}
