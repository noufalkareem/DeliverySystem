package com.mikkysoft.controller;

import java.util.ArrayList;
import java.util.List;

import com.mikkysoft.dbaccess.SectorDao;
import com.mikkysoft.dbaccess.UnitDao;
import com.mikkysoft.dbaccess.ZoneDao;
import com.mikkysoft.model.Circle;
import com.mikkysoft.model.Sector;
import com.mikkysoft.model.Unit;
import com.mikkysoft.model.Zone;

public class AdminController {
	
	public List<Circle> getAllCircles(){
		try {
			List<Circle> circles = new ArrayList<>();
			ZoneDao zoneDao = new ZoneDao();
			List<Zone> zones = zoneDao.getAll();
			circles.addAll(zones);
			SectorDao sectorDao = new SectorDao();
			List<Sector> sectors = sectorDao.getAll();
			circles.addAll(sectors);
			UnitDao unitDao = new UnitDao();
			List<Unit> units = unitDao.getAll();
			circles.addAll(units);
			return circles;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

}
