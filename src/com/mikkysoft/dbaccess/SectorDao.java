package com.mikkysoft.dbaccess;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.mikkysoft.model.Circle;
import com.mikkysoft.model.AccessType;
import com.mikkysoft.model.Sector;
import com.mikkysoft.model.Unit;
import com.mikkysoft.model.Zone;

public class SectorDao extends SuperCircleDao<Unit> {
	
	private UnitDao unitDao;

	public SectorDao() throws ClassNotFoundException, SQLException {
		super();
		unitDao = new UnitDao();

	}

	@Override
	public Sector read(int circleId) throws SQLException {
		return read(circleId, false);
	}
	
	public Sector read(int circleId, boolean lazyLoad) throws SQLException{
		Sector sector = (Sector) super.read(circleId);
		sector.setType(AccessType.SECTOR);
		if(!lazyLoad){
			sector.setUnits(getSubCircles(circleId));			
		}
		return sector;
		
	}
	
//	public List<Unit> getUnitsBySector(int sectorId) throws SQLException{
//		PreparedStatement pStatement = conn.prepareStatement("select unitid from circle where parentid = ?");
//		pStatement.setInt(1, sectorId);
//		ResultSet rs = pStatement.executeQuery();
//		List<Unit> units = new ArrayList<>();
//		while(rs.next()){
//			int unitId = rs.getInt("unitid");
//			Unit unit = unitDao.read(unitId);
//			units.add(unit);
//		}
//		return units;
//	}

	@Override
	public UnitDao getSubCircleDao() throws SQLException {
		// TODO Auto-generated method stub
		return unitDao;
	}

	@Override
	protected Circle getCircleObject() {
		// TODO Auto-generated method stub
		return new Sector();
	}
	
//	public void addUnitsToSector(int sectorId,List<Unit>) throws SQLException{
//		unitDao.create(parentId, c);
//	}

	public List<Sector> getAll() throws SQLException{
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("select unitid,name,parentid from circle where type = 'SECTOR'");
		List<Sector> sectors = new ArrayList<>();
		while(rs.next()){
			Sector sector = new Sector();
			sector.setUnitId(rs.getInt("unitid"));
			sector.setName(rs.getString("name"));
			sector.setType(AccessType.SECTOR);
			sectors.add(sector);
		}
		return sectors;
		
	}

	@Override
	protected Circle getParentCircleObject() {
		// TODO Auto-generated method stub
		return new Zone();
	}
	

}
