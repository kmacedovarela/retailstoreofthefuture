package com.demo.retailstoreofthefuture.model;

import java.awt.Point;

public class CustomerMovementEvent {
	private Point location;
	private long timestamp;

	private String id;
    private long ts;
    private int x;
    private int y;
	
	public CustomerMovementEvent(String customerId, Point location, long timestamp) {
		this.id = customerId;
		this.location = location;
		this.timestamp = timestamp;
	}

	public CustomerMovementEvent(String customerId, Point location) {
		this.id = customerId;
		this.location = location;
	}
	
	public CustomerMovementEvent() {}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getTs() {
		return this.ts;
	}

	public void setTs(long ts) {
		this.ts = ts;
	}

	public int getX() {
		return this.x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return this.y;
	}

	public void setY(int y) {
		this.y = y;
	}


	public String geId() {
		return id;
	}

	public String getCustomerId() {
		return id;
	}

	public void setCustomerId(String customerId) {
		this.id = customerId;
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
