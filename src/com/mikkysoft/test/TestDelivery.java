package com.mikkysoft.test;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.mikkysoft.controller.SubscriberController;
import com.mikkysoft.controller.UnitController;
import com.mikkysoft.controller.UserController;
import com.mikkysoft.model.Month;
import com.mikkysoft.model.Subscriber;
import com.mikkysoft.model.User;

public class TestDelivery {

	public static void main(String[] args) throws ClassNotFoundException,
			SQLException, ParseException {
		// TODO Auto-generated method stub
//		testSubscriberCreation();
//		
//		testDeliveryToSubscriber(Month.MAR, "08/03/2015");
		
//		testUnit();
		
		UserController controller = new UserController();
		User user = controller.read("ssharafiyyah");
		System.out.println(user);

	}

	public static void testSubscriberCreation() throws ClassNotFoundException,
			SQLException {
		Subscriber subscriber = new Subscriber();
		subscriber.setName("Faseen");
		subscriber.setYear(2015);
		subscriber.setLandmark("Markaz villa");
		subscriber.setMobile("9447459972");
		SubscriberController controller = new SubscriberController();
		int id = controller.create(1, subscriber);
		subscriber.setId(id);
		System.out.println(subscriber);
	}

	public static void testDeliveryToSubscriber(Month month, String date)
			throws SQLException, ClassNotFoundException, ParseException {
		SubscriberController controller = new SubscriberController();
		controller.updateDelivery(3, month.toString());

		Map<String, String> deliveryReport = controller
				.getDeliveryStatusForSubscriber(3);
		Set<String> keys = deliveryReport.keySet();
		for (String key : keys) {
			System.out.println("Month : " + key + " - Delivery date : " + deliveryReport.get(key));
		}
	}
	
	public static void testUnit() throws SQLException, ClassNotFoundException{
		UnitController controller = new UnitController();
		List<Subscriber> subscribers = controller.getSubscribersByUnit(1);
		for(Subscriber subscriber : subscribers){
			System.out.println(subscriber);
		}
	}

}
