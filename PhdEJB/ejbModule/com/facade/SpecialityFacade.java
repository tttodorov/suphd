package com.facade;

import java.util.List;

import javax.ejb.Local;

import com.model.Speciality;

@Local
public interface SpecialityFacade {

	public Speciality getById(Integer id);

	public List<Speciality> getAllSpecialityReportMes();
}
