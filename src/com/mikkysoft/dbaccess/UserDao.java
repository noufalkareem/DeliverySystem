package com.mikkysoft.dbaccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mikkysoft.model.AccessType;
import com.mikkysoft.model.Circle;
import com.mikkysoft.model.Sector;
import com.mikkysoft.model.Unit;
import com.mikkysoft.model.User;
import com.mikkysoft.model.Zone;

public class UserDao extends Dao {

	public UserDao() throws ClassNotFoundException, SQLException {
		super();
		// TODO Auto-generated constructor stub
	}

	public void create(User user) throws SQLException {
		PreparedStatement pStatement = conn
				.prepareStatement("insert into user(name,password,type) values(?,?,?)");
		pStatement.setString(1, user.getName());
		pStatement.setString(2, user.getPassword());
		pStatement.setString(3, user.getType().toString());
		pStatement.execute();
	}

	public User read(String name) throws SQLException {
		PreparedStatement pStatement = conn
				.prepareStatement("select a.password password, a.type type, a.circleid circleid, b.name circle_name from user a inner join circle b on a.circleid = b.unitid where a.name = ?");
		pStatement.setString(1, name);
		ResultSet rs = pStatement.executeQuery();
		User user = null;
		while (rs.next()) {
			user = new User();
			user.setName(name);
			user.setPassword(rs.getString("password"));
			user.setType(AccessType.valueOf(rs.getString("type")));
			int circleId = rs.getInt("circleid");
			Circle circle = null;
			if (user.getType().equals(AccessType.ZONE)) {
				circle = new Zone();
				circle.setType(AccessType.ZONE);

			}else if(user.getType().equals(AccessType.SECTOR)){
				circle = new Sector();
				circle.setType(AccessType.SECTOR);
				
			}else if(user.getType().equals(AccessType.UNIT)){
				circle = new Unit();
				circle.setType(AccessType.UNIT);				
			}else if(user.getType().equals(AccessType.ADMIN)){
				circle = new Unit();
				circle.setType(AccessType.ADMIN);
			}
			circle.setUnitId(circleId);
			circle.setName(rs.getString("circle_name"));
			user.setCircle(circle);
		}
		return user;
	}

}
