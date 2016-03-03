package com.mikkysoft.controller;

import java.sql.SQLException;
import java.util.List;

import com.mikkysoft.dbaccess.CircleDao;
import com.mikkysoft.dbaccess.SectorDao;
import com.mikkysoft.model.Circle;
import com.mikkysoft.model.Unit;

public class SectorController extends CircleController {
	
	private SectorDao sectorDao;
	
	public SectorController() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated constructor stub
		sectorDao = new SectorDao();
	}

	@Override
	public CircleDao getCircleDao() {
		// TODO Auto-generated method stub
		return sectorDao;
	}
	
	
	public List<Unit> getUnitsBySector(int sectorId) throws SQLException{
		return sectorDao.getSubCircles(sectorId);
	}
	
	public void addUnitsToSector(int sectorId,List<Unit> units) throws SQLException{
		sectorDao.addSubCircleToParent(sectorId, units);
	}

	@Override
	public List<Circle> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
