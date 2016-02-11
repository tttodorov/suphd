package com.facade;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;

import com.model.PhdReport;

@Local
public interface UserPhdReportFacade {

	public void save(PhdReport phdReport);

	public PhdReport update(PhdReport phdReport);

	public void delete(PhdReport phdReport);

	public PhdReport find(int entityID);

	public List<PhdReport> findAll();

	public ArrayList<Integer> getAllUserPhdReportYears(Integer userId);

	public ArrayList<PhdReport> getAllUserPhdReportByYear(Integer year);

	public ArrayList<PhdReport> getAllUserPhdReportByUserYear(
			Integer userId, Integer year);

	public PhdReport findReportById(Integer integer);

	public PhdReport findReportByYearMonthUser(Integer year, Integer month,
			Integer userId);

	public PhdReport getLast();

}