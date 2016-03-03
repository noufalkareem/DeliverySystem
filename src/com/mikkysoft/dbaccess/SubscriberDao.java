package com.mikkysoft.dbaccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import com.mikkysoft.model.Subscriber;

public class SubscriberDao extends Dao{
	
	
	
	public SubscriberDao() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated constructor stub
		super();
	}
	
	public int create(int unitId,Subscriber subscriber) throws SQLException{
		PreparedStatement pStatement =  conn.prepareStatement("insert into subscriber(id,name,unitid,mobile,landmark,year) values(?,?,?,?,?,?)",
				Statement.RETURN_GENERATED_KEYS);
		pStatement.setInt(1, subscriber.getId());
		pStatement.setString(2, subscriber.getName());
		pStatement.setInt(3, unitId);
		pStatement.setString(4, subscriber.getMobile());
		pStatement.setString(5, subscriber.getLandmark());
		pStatement.setInt(6, subscriber.getYear());
		pStatement.execute(); 
		ResultSet rs = pStatement.getGeneratedKeys();
		while(rs.next()){
			return rs.getInt(1);
		}
		return 0;		
	}
	
	public void delete(int subscriberId) throws SQLException{
		PreparedStatement pStatement = conn.prepareStatement("delete from subscriber where id = ?");
		pStatement.setInt(1, subscriberId);
		pStatement.execute(); 
	}
	
	public Subscriber read(int subscriberId) throws SQLException{
		PreparedStatement pStatement = conn.prepareStatement("select id,name,unitid,mobile,landmark from subscriber where id = ?");
		pStatement.setInt(1, subscriberId);
		ResultSet rs = pStatement.executeQuery();
		Subscriber subscriber = null;
		while(rs.next()){
			subscriber = new Subscriber();
			int subId = rs.getInt("id");
			subscriber.setId(subId);
			subscriber.setName(rs.getString("name"));
			subscriber.setMobile(rs.getString("mobile"));
			subscriber.setLandmark(rs.getString("landmark"));
			Map<String, String> deliveryStatus = getDeliveryStatus(subscriberId);
			subscriber.setDeliveryRecord(deliveryStatus);			
//			subscriber.setId(rs.getInt("id"));
		}
		return subscriber;
	}
	
//	public List<Subscriber> getSubscribersByUnit(int unitId) throws SQLException{
//		PreparedStatement pStatement = conn.prepareStatement("select id,name,unitid,mobile,landmark from subscriber where unitid = ?");
//		pStatement.setInt(1, unitId);
//		ResultSet rs = pStatement.executeQuery();
//		List<Subscriber> subscribers = new ArrayList<>();
//		
//		while(rs.next()){
//			Subscriber subscriber = new Subscriber();
//			int subscriberId = rs.getInt("id");
//			subscriber.setId(subscriberId);
//			subscriber.setName(rs.getString("name"));
//			subscriber.setMobile(rs.getString("mobile"));
//			subscriber.setLandmark(rs.getString("landmark"));
//			Map<String, String> deliveryStatus = getDeliveryStatus(subscriberId);
//			subscriber.setDeliveryRecord(deliveryStatus);
//			
//			subscribers.add(subscriber);
////			subscriber.setId(rs.getInt("id"));
//		}
//		return subscribers;	
//	}
	
	public Map<String, String> getDeliveryStatus(int subscriberId) throws SQLException{
		PreparedStatement pStatement = conn.prepareStatement("select id,userid,month,date from delivery_record where userid = ?");
		pStatement.setInt(1, subscriberId);
		ResultSet rs = pStatement.executeQuery();
		Map<String, String> delivery = new HashMap<String, String>();
		while(rs.next()){
			String deliveryDate = rs.getString("date");
			delivery.put(rs.getString("month"), deliveryDate);
		}
		return delivery;		
	}
	
	public void updateDelivery(int subscriberId,String month,String date) throws SQLException{
		PreparedStatement pStatement = conn.prepareStatement("insert into delivery_record(userid,month,date) values(?,?,?)");
		pStatement.setInt(1, subscriberId);
		pStatement.setString(2, month);
		pStatement.setString(3, date.toString());
		pStatement.execute();
		
	}
	
	

}
