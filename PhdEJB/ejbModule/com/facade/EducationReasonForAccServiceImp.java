package com.facade;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import com.dao.EducationReasonForAccDAO;
import com.model.EducationReasonForAcc;

@Stateless
@WebService(endpointInterface = "com.facade.EducationReasonForAccService", serviceName = "EducationReasonForAccService")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public class EducationReasonForAccServiceImp implements EducationReasonForAccService {

	@EJB
	private EducationReasonForAccDAO educationReasonForAccDAO;

	public EducationReasonForAccServiceImp() {
	}

	@Override
	public EducationReasonForAcc getById(Integer id) {
		return educationReasonForAccDAO.find(id);
	}
	
	@Override
	public List<EducationReasonForAcc> getAll() {
		return educationReasonForAccDAO.getAll();
	}
}