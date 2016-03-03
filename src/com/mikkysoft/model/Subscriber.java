package com.mikkysoft.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Subscriber {
	private int id;
	private String name;
	private String mobile;
	private String landmark;
	private int year;
	private Map<String, String> deliveryRecord;
//	private Unit unit;
	
	public Subscriber() {
		// TODO Auto-generated constructor stub
		deliveryRecord = new HashMap<String, String>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getLandmark() {
		return landmark;
	}

	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Map<String, String> getDeliveryRecord() {
		return deliveryRecord;
	}

	public void setDeliveryRecord(Map<String, String> deliveryRecord) {
		this.deliveryRecord = deliveryRecord;
	}
	
	public void addDeliveryRecord(String month,String date){
		deliveryRecord.put(month, date);
	}
	
	public String getDeliveryStatusByMonth(String month){
		return deliveryRecord.get(month);
	}

	@Override
	public String toString() {
		return "Subscriber [id=" + id + ", name=" + name + ", mobile=" + mobile
				+ ", landmark=" + landmark + ", year=" + year + "]";
	}
	
	

}
