package com.mikkysoft.model;

public enum AccessType {
	UNIT, SECTOR, ZONE, ADMIN, NONE;

	public String toString() {
		switch (this) {
		case UNIT:
			return "UNIT";
		case SECTOR:
			return "SECTOR";
		case ZONE:
			return "ZONE";
		case ADMIN:
			return "ADMIN";
		default:
			return "UNIT";
		}
	}

}
