package com.firestork.xekhach;

import java.util.ArrayList;

public class ThanhPho {

	private long id;
	private String name;
	public ArrayList<Long> listBen; 
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<Long> getListBen() {
		return listBen;
	}
	public void setListBen(ArrayList<Long> listBen) {
		this.listBen = listBen;
	}

}

