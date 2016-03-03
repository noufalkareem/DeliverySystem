package com.mikkysoft.model;

import java.util.List;

public abstract class Circle {
	private int unitId;
	private String name;
	private AccessType type;
	private Circle parent;

	public int getUnitId() {
		return unitId;
	}

	public void setUnitId(int unitId) {
		this.unitId = unitId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public AccessType getType() {
		return type;
	}

	public void setType(AccessType type) {
		this.type = type;
	}
	
	public Circle getParent() {
		return parent;
	}

	public void setParent(Circle parent) {
		this.parent = parent;
	}

	public abstract int getNumberOfSubscribers();
	
	public abstract int getDeliveredNumberByMonth(Month month);
	
	

}
