package com.firestork.xekhach;

import java.util.ArrayList;


public class NhaXe {
	
	private long id;
	private String transportName;
	public ArrayList<Long> listRoute;
	
	
	
	public long getId() {
		return id;
	}
	public void setId(long l) {
		this.id = l;
	}
	public String getTransportName() {
		return transportName;
	}
	public void setTransportName(String transportName) {
		this.transportName = transportName;
	}
	public ArrayList<Long> getListRoute() {
		return listRoute;
	}
	public void setListRoute(ArrayList<Long> listRoute) {
		this.listRoute = listRoute;
	}
	
}
