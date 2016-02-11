package com.facade;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import com.dao.GroupDAO;
import com.model.Group;

@Stateless
@WebService(endpointInterface = "com.facade.GroupService", serviceName = "GroupService")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public class GroupServiceImp implements GroupService {

	@EJB
	private GroupDAO groupsDAO;

	public GroupServiceImp() {
	}

	@Override
	public Group getById(Integer id) {
		return groupsDAO.find(id);
	}
	
	@Override
	public List<Group> getAll() {
		return groupsDAO.getAll();
	}
}