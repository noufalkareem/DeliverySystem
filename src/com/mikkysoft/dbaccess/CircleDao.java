package com.mikkysoft.dbaccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mikkysoft.model.Circle;
import com.mikkysoft.model.AccessType;


public abstract class CircleDao extends Dao {
	
	
	
	public CircleDao() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated constructor stub
		super();
	
	}
	
	public void create(int parentId,Circle c) throws SQLException{
		PreparedStatement pStatement = conn.prepareStatement("insert into circle(name,type,parentid) values(?,?,?)");
		pStatement.setString(1, c.getName());
		pStatement.setString(2, c.getType().toString());
		pStatement.setInt(3, parentId);
		pStatement.execute();
	}
	
	public void edit(int parentId,Circle c) throws SQLException{
		PreparedStatement pStatement = conn.prepareStatement("update circle set name = ?,type = ?,parentid =? where unitid = ?");
		pStatement.setString(1, c.getName());
		pStatement.setString(2, c.getType().toString());
		pStatement.setInt(3, parentId);
		pStatement.setInt(4, c.getUnitId());
		pStatement.execute();		
	}
	
	public Circle read(int circleId) throws SQLException{
		PreparedStatement pStatement = conn.prepareStatement("SELECT a.unitid unitid,a.name name,a.type type,"
				+ " a.parentid parentid,b.name parentname,b.type parenttype"
				+ " FROM risaladb.circle a inner join risaladb.circle b"
				+ " on a.parentid = b.unitid and a.unitid = ?");
		pStatement.setInt(1, circleId);
		ResultSet rs = pStatement.executeQuery();
		Circle circle = getCircleObject();
		while(rs.next()){
			circle.setUnitId(circleId);
			circle.setName(rs.getString("name"));
			circle.setType(AccessType.valueOf(rs.getString("type")));
			Circle parentCircle = getParentCircleObject();
			parentCircle.setUnitId(rs.getInt("parentid"));
			parentCircle.setName(rs.getString("parentname"));
			parentCircle.setType(AccessType.valueOf(rs.getString("parenttype")));
			circle.setParent(parentCircle);
		}
		return circle;
		
	}
	
	protected abstract Circle getCircleObject();
	
	protected abstract Circle getParentCircleObject();
	
	public void delete(int circleId) throws SQLException{
		PreparedStatement pStatement = conn.prepareStatement("delete from circle where unitid = ?");
		pStatement.setInt(1, circleId);
		pStatement.execute();
	}
	
//	public Map<String, String> getCircleDetails(Integer circleId) throws SQLException{
//		PreparedStatement pStatement = conn.prepareStatement("select name,type,parentid from circle where unitid = ?");
//		ResultSet rs = pStatement.executeQuery();
//		Map<String, String> circleDetails = new HashMap<String, String>();
//		while(rs.next()){
//			circleDetails.put("unitid", circleId.toString());
//			circleDetails.put("name", rs.getString("name"));
//			circleDetails.put("type", rs.getString("type"));
//		}
//		return circleDetails;
//
//	}
	
	
	
}
