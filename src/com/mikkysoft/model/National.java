package com.mikkysoft.model;

import java.util.ArrayList;
import java.util.List;

public class National extends Circle {
	
	private List<Zone> zones;
	
	public National(){
		zones = new ArrayList<>();
	}

	@Override
	public int getNumberOfSubscribers() {
		// TODO Auto-generated method stub
		int numberOfSubscribers = 0;
		for(Zone zone:zones){
			numberOfSubscribers += zone.getNumberOfSubscribers();
		}
		return numberOfSubscribers;

	}

	@Override
	public int getDeliveredNumberByMonth(Month month) {
		// TODO Auto-generated method stub
		int number = 0;
		for(Zone zone : zones){
			number += zone.getDeliveredNumberByMonth(month);
		}
		return number;
	}

	public List<Zone> getZones() {
		return zones;
	}

	public void setZones(List<Zone> zones) {
		this.zones = zones;
	}

}
