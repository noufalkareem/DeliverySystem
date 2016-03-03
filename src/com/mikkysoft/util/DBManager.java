package com.mikkysoft.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {
	private static final String className = "com.mysql.jdbc.Driver";
	private static final String url = "jdbc:mysql://localhost/risaladb";
	private Connection conn = null;
	
	public DBManager() throws ClassNotFoundException, SQLException{
		Class.forName(className);
		conn = DriverManager.getConnection(url, "risala", "changed123");		
	}
	
	public Connection getConnection(){
		return conn;
	}

}
