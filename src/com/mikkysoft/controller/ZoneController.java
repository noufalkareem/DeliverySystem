package com.mikkysoft.controller;

import java.sql.SQLException;
import java.util.List;

import com.mikkysoft.dbaccess.CircleDao;
import com.mikkysoft.dbaccess.ZoneDao;
import com.mikkysoft.model.Circle;
import com.mikkysoft.model.Sector;
import com.mikkysoft.model.Zone;

public class ZoneController extends CircleController{
	
	private ZoneDao zoneDao;
	
	public ZoneController() {
		// TODO Auto-generated constructor stub
		try {
			zoneDao = new ZoneDao();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	public Zone read(int zoneId) {
		try {
			return zoneDao.read(zoneId);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Sector> getSectors(int zoneId){
		try {
			return zoneDao.getSubCircles(zoneId);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public CircleDao getCircleDao() {
		// TODO Auto-generated method stub
		return zoneDao;
	}

	@Override
	public List<Circle> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
