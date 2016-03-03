package com.mikkysoft.controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.mikkysoft.dbaccess.SubscriberDao;
import com.mikkysoft.model.Subscriber;

public class SubscriberController {
	private SubscriberDao subscriberDao;
	
	public SubscriberController() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated constructor stub
		subscriberDao = new SubscriberDao();
	}
	
	public int create(int unitId, Subscriber subscriber) throws SQLException{
		return subscriberDao.create(unitId, subscriber);
	}
	
	public void updateDelivery(int subscriberId,String month){
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			subscriberDao.updateDelivery(subscriberId, month.toString(),
					dateFormat.format(new Date()));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
//		subscriberDao.updateDelivery(subscriberId, month, date);
	}
	
	public Subscriber getSubscriberById(int subscriberId) throws SQLException{
		return subscriberDao.read(subscriberId);
	}
	
//	public List<Subscriber> getSubscribersByUnit(int unitId) throws SQLException{
//		return subscriberDao.getSubscribersByUnit(unitId);
//	}
//	
//	public void addSubscribersToUnit(int unitId, List<Subscriber> subscribers) throws SQLException{
//		for(Subscriber subscriber : subscribers){
//			create(unitId, subscriber);
//		}
//	}
	
	public Map<String, String> getDeliveryStatusForSubscriber(int subscriberId) throws SQLException, ParseException{
		return subscriberDao.getDeliveryStatus(subscriberId);
	}

}
