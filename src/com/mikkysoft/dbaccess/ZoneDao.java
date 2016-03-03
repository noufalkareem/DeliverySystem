package com.mikkysoft.dbaccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.mikkysoft.model.Circle;
import com.mikkysoft.model.AccessType;
import com.mikkysoft.model.Sector;
import com.mikkysoft.model.Zone;

public class ZoneDao extends SuperCircleDao<Sector> {
	private SectorDao sectorDao;

	public ZoneDao() throws ClassNotFoundException, SQLException {
		super();
		sectorDao = new SectorDao();

	}

	@Override
	public Zone read(int circleId) throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement pStatement = conn.prepareStatement("SELECT a.unitid unitid,a.name name,a.type type"
				+ " FROM risaladb.circle a where a.unitid = ?");
		pStatement.setInt(1, circleId);
		ResultSet rs = pStatement.executeQuery();
		Zone zone = new Zone();
		while(rs.next()){
			zone.setUnitId(circleId);
			zone.setName(rs.getString("name"));
			zone.setType(AccessType.valueOf(rs.getString("type")));
			zone.setParent(null);
		}
		return zone;
	}
	

	@Override
	public CircleDao getSubCircleDao() throws SQLException {
		// TODO Auto-generated method stub
		return sectorDao;
	}

	@Override
	protected Circle getCircleObject() {
		// TODO Auto-generated method stub
		return new Zone();
	}
	
	public List<Zone> getAll() throws SQLException{
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("select unitid,name,parentid from circle where type = 'ZONE'");
		List<Zone> zones = new ArrayList<>();
		while(rs.next()){
			Zone zone = new Zone();
			zone.setUnitId(rs.getInt("unitid"));
			zone.setName(rs.getString("name"));
			zone.setType(AccessType.ZONE);
			zones.add(zone);
		}
		return zones;
	}

	@Override
	protected Circle getParentCircleObject() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	

}
