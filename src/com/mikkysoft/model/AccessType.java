package com.mikkysoft.model;

public enum AccessType {
	UNIT, SECTOR, ZONE, NATIONAL, ADMIN, NONE;

	public String toString() {
		switch (this) {
		case UNIT:
			return "UNIT";
		case SECTOR:
			return "SECTOR";
		case ZONE:
			return "ZONE";
		case NATIONAL:
			return "NATIONAL";
		case ADMIN:
			return "ADMIN";
		default:
			return "UNIT";
		}
	}

}
