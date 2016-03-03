package com.mikkysoft.controller;

import java.sql.SQLException;

import com.mikkysoft.dbaccess.UserDao;
import com.mikkysoft.model.User;

public class UserController {
	
	private UserDao userDao;
	
	private static User loggedUser;
	
	public static User getLoggedUser() {
		return loggedUser;
	}

	public static void setLoggedUser(User user) {
		loggedUser = user;
	}

	public UserController() throws  SQLException {
		try {
			// TODO Auto-generated constructor stub
			userDao = new UserDao();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void create(User user){
		
	}
	
	public User read(String name) throws SQLException{
		return  userDao.read(name); 
	}
	
	
}
