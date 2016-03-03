package com.mikkysoft.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.mikkysoft.dbaccess.CircleDao;
import com.mikkysoft.model.Circle;

public abstract class CircleController {
	
	
	public abstract CircleDao getCircleDao();
	
	public void create(int parentId,Circle circle) throws SQLException{
		getCircleDao().create(parentId, circle);
	}
	
	public void edit(int parentId,Circle circle) throws SQLException{
		getCircleDao().edit(parentId, circle);
	}
	
	public Circle readCircle(int circleId) throws SQLException{
		return getCircleDao().read(circleId);
	}
	
	public void delete(int circleId) throws SQLException{
		getCircleDao().delete(circleId);
	}
	
	public abstract List<Circle> getAll(); 

}
