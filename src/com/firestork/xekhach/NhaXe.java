package com.firestork.xekhach;

import java.util.ArrayList;


public class NhaXe {
	
	private int id;
	private String transportName;
	private String phoneNumber;
	private ArrayList<TuyenXe> listRoute;
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTransportName() {
		return transportName;
	}
	public void setTransportName(String transportName) {
		this.transportName = transportName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public ArrayList<TuyenXe> getListRoute() {
		return listRoute;
	}
	public void setListRoute(ArrayList<TuyenXe> listRoute) {
		this.listRoute = listRoute;
	}
	
}
