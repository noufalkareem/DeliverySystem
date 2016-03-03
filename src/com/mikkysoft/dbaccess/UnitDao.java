package com.mikkysoft.dbaccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mikkysoft.model.Circle;
import com.mikkysoft.model.AccessType;
import com.mikkysoft.model.Sector;
import com.mikkysoft.model.Subscriber;
import com.mikkysoft.model.Unit;

public class UnitDao extends CircleDao {
	
	private SubscriberDao subscriberDao;

	public UnitDao() throws ClassNotFoundException, SQLException {
		super();
		subscriberDao = new SubscriberDao();
	}

	@Override
	public Unit read(int circleId) throws SQLException {
		// TODO Auto-generated method stub
		return read(circleId, false);
	}

	public Unit read(int circleId,boolean lazyLoad) throws SQLException {
		// TODO Auto-generated method stub
		Unit unit = (Unit) super.read(circleId);
		if(!lazyLoad){
			unit.setSubscribers(getSubscribersByUnit(circleId));			
		}
		return unit;
	}
	
	
	public List<Subscriber> getSubscribersByUnit(int unitId) throws SQLException{
		PreparedStatement pStatement = conn.prepareStatement("select id from subscriber where unitid = ?");
		pStatement.setInt(1, unitId);
		ResultSet rs = pStatement.executeQuery();
		List<Subscriber> subscribers = new ArrayList<>();
		while(rs.next()){
			int subscriberId = rs.getInt("id");
			Subscriber subscriber = subscriberDao.read(subscriberId);						
			subscribers.add(subscriber);
		}
		return subscribers;	
	}
	
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

	@Override
	protected Circle getCircleObject() {
		// TODO Auto-generated method stub
		return new Unit();
	}
	
	public List<Unit> getAll() throws SQLException{
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("select unitid,name,parentid from circle where type = 'UNIT'");
		List<Unit> units = new ArrayList<>();
		while(rs.next()){
			Unit unit = new Unit();
			unit.setUnitId(rs.getInt("unitid"));
			unit.setName(rs.getString("name"));
			unit.setType(AccessType.UNIT);
			units.add(unit);
		}
		return units;
	
	}

	@Override
	protected Circle getParentCircleObject() {
		// TODO Auto-generated method stub
		return new Sector();
	}

	
	
	
}
