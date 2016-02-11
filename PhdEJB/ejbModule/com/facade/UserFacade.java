package com.facade;

import java.util.List;

import javax.ejb.Local;

import com.model.User;

@Local
public interface UserFacade {

	public User findByUsername(String username);

	public User getById(Integer userId);

	public User findByUsernamePassword(String username, String password);

	public List<User> getAllUserPhd();

	public List<User> getAllUserPhdForeign();

	public List<User> findAllUserAdmin();

	public abstract User update(User user);

	public abstract List<User> findAll();

	public void save(User user);
	
	public User getLast();

	public List<User> getAllUserPhdReportMes();
	
	public List<User> getAllUserPhdForeignReportMes();

	public User getPreviousById(Integer userId);

	public User getNextById(Integer userId);
}
