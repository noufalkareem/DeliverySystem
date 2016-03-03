package com.mikkysoft.dbaccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mikkysoft.model.AccessType;
import com.mikkysoft.model.Circle;
import com.mikkysoft.model.National;
import com.mikkysoft.model.Zone;
//data access for the National
public class NationalDao extends SuperCircleDao<Zone> {
	//used for collecting the zone data
	private ZoneDao zoneDao;

	public NationalDao() throws ClassNotFoundException, SQLException {
		super();
		zoneDao = new ZoneDao();
	}
	
	
	

	@Override
	public National read(int circleId) throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement pStatement = conn.prepareStatement("SELECT a.unitid unitid,a.name name,a.type type"
				+ " FROM risaladb.circle a where a.unitid = ?");
		pStatement.setInt(1, circleId);
		ResultSet rs = pStatement.executeQuery();
		National national = new National();
		while(rs.next()){
			national.setUnitId(circleId);
			national.setName(rs.getString("name"));
			national.setType(AccessType.valueOf(rs.getString("type")));
			national.setParent(null);
		}
		return national;

	}

	public List<National> getAll() throws SQLException{
		//return the list of all the nationals. For GC
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("select unitid,name,parentid from circle where type = 'NATIONAL'");
		List<National> nationals = new ArrayList<>();
		while(rs.next()){
			National national = new National();
			national.setUnitId(rs.getInt("unitid"));
			national.setName(rs.getString("name"));
			national.setType(AccessType.NATIONAL);
			nationals.add(national);
		}
		return nationals;
	}
	



	@Override
	public CircleDao getSubCircleDao() throws SQLException {
		// get the data access object of the circle below. For National it is zone
		return zoneDao;
	}

	@Override
	protected Circle getCircleObject() {
		// getting the instance of the current circle
		return new National();
	}

	@Override
	protected Circle getParentCircleObject() {
		// TODO Auto-generated method stub
		//should return GC object if implemented
		return null;
	}

}
