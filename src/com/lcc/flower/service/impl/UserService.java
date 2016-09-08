package com.lcc.flower.service.impl;

import java.util.List;

import com.lcc.flower.dao.IUserDAO;
import com.lcc.flower.model.User;
import com.lcc.flower.service.IUserService;

public class UserService implements IUserService{

	private IUserDAO userDAO;
	
	public IUserDAO getUserDAO() {
		return userDAO;
	}
	
	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	@Override
	public boolean addOrUpdateUser(User user) {
		return userDAO.addOrUpdateUser(user);
	}

	@Override
	public User checkUser(User user) {
		return userDAO.checkUser(user);
	}

	@Override
	public boolean guashiUser(int id) {
		return userDAO.guashiUser(id);
	}

	@Override
	public boolean jieguaUser(int id) {
		return userDAO.jieguaUser(id);
	}

	@Override
	public List getGuashi() {
		return userDAO.getGuashi();
	}

}
