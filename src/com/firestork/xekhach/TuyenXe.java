package com.firestork.xekhach;

import java.util.ArrayList;

public class TuyenXe {

	private long routeID;
	private long operatorID;
	private long fromStopID;
	private long tostopID;
	private long scheduleID;

	private String routeName;
	private static ArrayList<Long> startTime;
	private static  ArrayList<Long> stopTime;
	
	// thời gian kiểu string 
	
	private String listStartTime;
	private String listStopTime;
	private String totalTime;
	private String price;
	private String seatInfo;
	private ArrayList<String> benifit;
	private String phone;
	
	

	
	
	public String getListStartTime() {
		return listStartTime;
	}

	public void setListStartTime(String listStartTime) {
		this.listStartTime = listStartTime;
	}

	public String getListStopTime() {
		return listStopTime;
	}

	public void setListStopTime(String listStopTime) {
		this.listStopTime = listStopTime;
	}

	public long getScheduleID() {
		return scheduleID;
	}

	public void setScheduleID(long scheduleID) {
		this.scheduleID = scheduleID;
	}

	public static String listFromTime() {
		String str = "[";
		for (int i = 0; i < startTime.size(); i++) {
			if (i == startTime.size() - 1) {
				str = str + startTime.get(i);
			} else {
				str = str + startTime.get(i) + ",";
			}
		}
		str = str + "]";
		return str;
	}

	public static String listStopTime() {
		String str = "[";
		for (int i = 0; i < stopTime.size(); i++) {
			if (i == stopTime.size() - 1) {
				str = str + stopTime.get(i);
			} else {
				str = str + stopTime.get(i) + ",";
			}
		}
		str = str + "]";
		return str;
	}

	public String listBenefit() {
		String str = "[";
		for (int i = 0; i < benifit.size(); i++) {
			if (i == benifit.size() - 1) {
				str = str + benifit.get(i);
			} else {
				str = str + benifit.get(i) + ",";
			}
		}
		str = str + "]";
		return str;
	}

	
	
	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public ArrayList<Long> getStopTime() {
		return stopTime;
	}

	public void setStopTime(ArrayList<Long> stopTime) {
		this.stopTime = stopTime;
	}

	public ArrayList<Long> getStartTime() {
		return startTime;
	}

	public void setStartTime(ArrayList<Long> startTime) {
		this.startTime = startTime;
	}

	public ArrayList<String> getBenifit() {
		return benifit;
	}

	public void setBenifit(ArrayList<String> benifit) {
		this.benifit = benifit;
	}

	public long getRouteID() {
		return routeID;
	}

	public void setRouteID(long routeID) {
		this.routeID = routeID;
	}

	public long getOperatorID() {
		return operatorID;
	}

	public void setOperatorID(long operatorID) {
		this.operatorID = operatorID;
	}

	public long getFromStopID() {
		return fromStopID;
	}

	public void setFromStopID(long fromStopID) {
		this.fromStopID = fromStopID;
	}

	public long getTostopID() {
		return tostopID;
	}

	public void setTostopID(long tostopID) {
		this.tostopID = tostopID;
	}

	public String getRouteName() {
		return routeName;
	}

	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}

	public String getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(String totalTime) {
		this.totalTime = totalTime;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getSeatInfo() {
		return seatInfo;
	}

	public void setSeatInfo(String seatInfo) {
		this.seatInfo = seatInfo;
	}

	public TuyenXe() {
	}

	@Override
	public String toString() {
		return "TuyenXe [routeID=" + routeID + ", operatorID=" + operatorID
				+ ", fromStopID=" + fromStopID + ", tostopID=" + tostopID
				+ ", routeName=" + routeName + ", startTime=" + startTime
				+ ", stopTime=" + stopTime + ", totalTime=" + totalTime
				+ ", price=" + price + ", seatInfo=" + seatInfo + "]";
	}

}
