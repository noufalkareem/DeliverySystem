package com.mikkysoft.model;

import java.util.ArrayList;
import java.util.List;

public class Sector extends Circle {

	private List<Unit> units;

	public Sector() {
		// TODO Auto-generated constructor stub
		units = new ArrayList<>();
	}

	public List<Unit> getUnits() {
		return units;
	}

	public void setUnits(List<Unit> units) {
		this.units = units;
	}

	public void addUnit(Unit unit) {
		units.add(unit);
	}

	@Override
	public int getNumberOfSubscribers() {
		// TODO Auto-generated method stub
		int numberOfSubscribers = 0;
		for(Unit unit : units){
			numberOfSubscribers += unit.getNumberOfSubscribers();
		}
		return numberOfSubscribers;
	}

	@Override
	public int getDeliveredNumberByMonth(Month month) {
		// TODO Auto-generated method stub
		int number = 0;
		for(Unit unit : units){
			number += unit.getDeliveredNumberByMonth(month);
		}
		return number;
	}

}
