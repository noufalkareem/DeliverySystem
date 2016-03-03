package com.mikkysoft.model;

import java.util.ArrayList;
import java.util.List;

public class Unit extends Circle {
	private List<Subscriber> subscribers;

	public Unit() {
		// TODO Auto-generated constructor stub
		subscribers = new ArrayList<>();
	}

	public List<Subscriber> getSubscribers() {
		return subscribers;
	}

	public void setSubscribers(List<Subscriber> subscribers) {
		this.subscribers = subscribers;
	}
	
	public void addSubscriber(Subscriber subscriber){
		subscribers.add(subscriber);
	}

	@Override
	public int getNumberOfSubscribers() {
		// TODO Auto-generated method stub
		return subscribers.size();
	}

	@Override
	public int getDeliveredNumberByMonth(Month month) {
		// TODO Auto-generated method stub
		int number = 0;
		for(Subscriber subscriber : subscribers){
			if(subscriber.getDeliveryStatusByMonth(month.toString())!=null){
				number++;
			}
		}
		return number;
	}
	
	

}
