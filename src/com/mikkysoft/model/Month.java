package com.mikkysoft.model;

public enum Month {
	 JUL, AUG, SEP, OCT, NOV, DEC,JAN, FEB, MAR, APR, MAY, JUN;

	public String toString() {
		switch (this) {
		case JAN:
			return "JAN";
		case FEB:
			return "FEB";
		case MAR:
			return "MAR";
		case APR:
			return "APR";
		case MAY:
			return "MAY";
		case JUN:
			return "JUN";
		case JUL:
			return "JUL";
		case AUG:
			return "AUG";
		case SEP:
			return "SEP";
		case OCT:
			return "OCT";
		case NOV:
			return "NOV";
		case DEC:
			return "DEC";
		default:
			return "JAN";
		}
	}

}
