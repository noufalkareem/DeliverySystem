package com.mikkysoft.dbaccess;

import java.sql.Connection;
import java.sql.SQLException;

import com.mikkysoft.util.DBManager;

public abstract class Dao {
	
	protected Connection conn;
	
	public Dao() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated constructor stub
		conn = new DBManager().getConnection();
	}

}
