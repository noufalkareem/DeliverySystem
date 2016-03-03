package com.mikkysoft.controller;

import java.sql.SQLException;
import java.util.List;

import com.mikkysoft.dbaccess.CircleDao;
import com.mikkysoft.dbaccess.NationalDao;
import com.mikkysoft.model.Circle;
import com.mikkysoft.model.National;
import com.mikkysoft.model.Zone;

public class NationalController extends CircleController {
	
	private NationalDao nationalDao;
	
	

	public NationalController() {
		try {
			nationalDao = new NationalDao();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public National read(int circleId){
		try {
			return nationalDao.read(circleId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public CircleDao getCircleDao() {
		// TODO Auto-generated method stub
		return nationalDao;
	}
	
	
	public List<Zone> getZones(int nationalId){
		try {
			return nationalDao.getSubCircles(nationalId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Circle> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
