package com.mikkysoft.model;

import java.util.ArrayList;
import java.util.List;

public class Zone extends Circle {

	private List<Sector> sectors;

	public Zone() {
		// TODO Auto-generated constructor stub
		sectors = new ArrayList<>();
	}

	public List<Sector> getSectors() {
		return sectors;
	}

	public void setSectors(List<Sector> sectors) {
		this.sectors = sectors;
	}

	@Override
	public int getNumberOfSubscribers() {
		// TODO Auto-generated method stub
		int numberOfSubscribers = 0;
		for(Sector sector:sectors){
			numberOfSubscribers += sector.getNumberOfSubscribers();
		}
		return numberOfSubscribers;
		
	}

	@Override
	public int getDeliveredNumberByMonth(Month month) {
		// TODO Auto-generated method stub
		int number = 0;
		for(Sector sector : sectors){
			number += sector.getDeliveredNumberByMonth(month);
		}
		return number;
	}

}
