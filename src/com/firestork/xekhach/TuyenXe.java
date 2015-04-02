package com.firestork.xekhach;

public class TuyenXe {

	private int id;
	private NhaXe tranpsort;
	private BenXe froStop;
	private BenXe tostop;
	
	private String routeName;
	private long startTime;
	private long stopTime;
	private long totalTime;
	private String price;
	private String seatInfo;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public NhaXe getTranpsort() {
		return tranpsort;
	}
	public void setTranpsort(NhaXe tranpsort) {
		this.tranpsort = tranpsort;
	}
	public BenXe getFroStop() {
		return froStop;
	}
	public void setFroStop(BenXe froStop) {
		this.froStop = froStop;
	}
	public BenXe getTsotop() {
		return tostop;
	}
	public void setTsotop(BenXe tsotop) {
		this.tostop = tsotop;
	}
	public String getRouteName() {
		return routeName;
	}
	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}
	public long getStartTime() {
		return startTime;
	}
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}
	public long getStopTime() {
		return stopTime;
	}
	public void setStopTime(long stopTime) {
		this.stopTime = stopTime;
	}
	public long getTotalTime() {
		return totalTime;
	}
	public void setTotalTime(long totalTime) {
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
	
	
	
	
	
	
	
	
}
