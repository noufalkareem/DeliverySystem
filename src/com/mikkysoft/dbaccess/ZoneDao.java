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
import com.mikkysoft.model.National;
import com.mikkysoft.model.Sector;
import com.mikkysoft.model.Zone;
//class to make the data access for the zone details
public class ZoneDao extends SuperCircleDao<Sector> {
	//access sector details for zone
	private SectorDao sectorDao;

	public ZoneDao() throws ClassNotFoundException, SQLException {
		super();
		sectorDao = new SectorDao();

	}

	@Override
	public Zone read(int circleId) throws SQLException {
		// TODO Auto-generated method stub
		
		Zone zone = (Zone) super.read(circleId);
		zone.setType(AccessType.ZONE);
		zone.setSectors(getSubCircles(circleId));			
		return zone;		
		
		
		
//		PreparedStatement pStatement = conn.prepareStatement("SELECT a.unitid unitid,a.name name,a.type type"
//				+ " FROM risaladb.circle a where a.unitid = ?");
//		pStatement.setInt(1, circleId);
//		ResultSet rs = pStatement.executeQuery();
//		Zone zone = new Zone();
//		while(rs.next()){
//			zone.setUnitId(circleId);
//			zone.setName(rs.getString("name"));
//			zone.setType(AccessType.valueOf(rs.getString("type")));
//			zone.setParent(null);
//		}
//		return zone;
	}
	

	@Override
	public CircleDao getSubCircleDao() throws SQLException {
		// return the data access object for the circle below. For Zone it is Sector
		return sectorDao;
	}

	@Override
	protected Circle getCircleObject() {
		// return a new instance of the current circle
		return new Zone();
	}
	
	public List<Zone> getAll() throws SQLException{
		//return the list of all the zones.
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
		// returns the instance of the Circle above. For zone it is National
		return new National();
	}
	
	
	
	

}
