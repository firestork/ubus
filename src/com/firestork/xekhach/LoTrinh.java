package com.firestork.xekhach;

import java.util.ArrayList;

public class LoTrinh {

	private long scheduleID;
	private long routerID;
	private long fromStopID;
	private long toStopID;
	private String str;
	
	
	
	public long getFromStopID() {
		return fromStopID;
	}
	public void setFromStopID(long fromStopID) {
		this.fromStopID = fromStopID;
	}
	public long getToStopID() {
		return toStopID;
	}
	public void setToStopID(long toStopID) {
		this.toStopID = toStopID;
	}
	public long getScheduleID() {
		return scheduleID;
	}
	public void setScheduleID(long scheduleID) {
		this.scheduleID = scheduleID;
	}
	public long getRouterID() {
		return routerID;
	}
	public void setRouterID(long routerID) {
		this.routerID = routerID;
	}
	public String getStr() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
	}
	
	
	
}
