package com.facade;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.dao.UserDAO;
import com.model.User;

@Stateless
public class UserFacadeImp implements UserFacade {

	@EJB
	private UserDAO userDAO;

	public User findByUsername(String username) {
		return userDAO.findByUsername(username);
	}

	public User getById(Integer userId) {
		return userDAO.getById(userId);
	}

	public User findByUsernamePassword(String username, String password) {
		return userDAO.findByUsernamePassword(username, password);
	}

	public List<User> getAllUserPhd() {
		return userDAO.getAllUserPhd();
	}

	public List<User> getAllUserPhdForeign() {
		return userDAO.getAllUserPhdForeign();
	}

	public List<User> findAllUserAdmin() {
		return userDAO.findAllUserAdmin();
	}

	@Override
	public User update(User user) {
		return userDAO.update(user);
	}

	@Override
	public List<User> findAll() {
		return userDAO.findAll();
	}

	@Override
	public void save(User user) {
		userDAO.save(user);
	}

	@Override
	public User getLast() {
		return userDAO.getLast();
	}

	@Override
	public List<User> getAllUserPhdReportMes() {
		return userDAO.getAllUserPhdReportMes();
	}

	@Override
	public List<User> getAllUserPhdForeignReportMes() {
		return userDAO.getAllUserPhdForeignReportMes();
	}

	public User getPreviousById(Integer userId) {
		return userDAO.getPreviousById(userId);
	}

	public User getNextById(Integer userId) {
		return userDAO.getNextById(userId);
	}
}