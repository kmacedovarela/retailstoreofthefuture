package com.demo.retailstoreofthefuture.model;

import java.awt.Point;

public class CustomerMovementEvent {

	public CustomerMovementEvent(String customerId, Point location) {
		this.customerId = customerId;
		this.location = location;
	}
	
	public CustomerMovementEvent() {super();}

	private String customerId;
	private Point location;
	private long timestamp;

	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public Point getLocation() {
		return location;
	}
	public void setLocation(Point location) {
		this.location = location;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

}
