package com.facade;

import java.util.List;

import javax.ejb.Local;

import com.model.EducationReasonForAcc;

@Local
public interface EducationReasonForAccFacade {

	public EducationReasonForAcc getById(Integer id);
	
	public List<EducationReasonForAcc> getAll();
}
