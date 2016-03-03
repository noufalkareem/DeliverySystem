package com.mikkysoft.controller;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.mikkysoft.dbaccess.CircleDao;
import com.mikkysoft.dbaccess.SubscriberDao;
import com.mikkysoft.dbaccess.UnitDao;
import com.mikkysoft.model.Circle;
import com.mikkysoft.model.Month;
import com.mikkysoft.model.Subscriber;

public class UnitController extends CircleController {
	
	private SubscriberDao subscriberDao;
	private UnitDao unitDao;
	
	public UnitController() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated constructor stub
		subscriberDao = new SubscriberDao();
		unitDao = new UnitDao();
	}
	
	public List<Subscriber> getSubscribersByUnit(int unitId)
			throws SQLException {
		return unitDao.getSubscribersByUnit(unitId);
	}

	public void addSubscribersToUnit(int unitId, List<Subscriber> subscribers)
			throws SQLException {
		for (Subscriber subscriber : subscribers) {
			subscriberDao.create(unitId, subscriber);
		}
	}

	@Override
	public CircleDao getCircleDao() {
		// TODO Auto-generated method stub
		return unitDao;
	}

	@Override
	public List<Circle> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
//	public void updateDelivery(int subscriberId,Month month,String comment) {
//		try {
//			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//			subscriberDao.updateDelivery(subscriberId, month.toString(),
//					dateFormat.format(new Date()));
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//	}

}
