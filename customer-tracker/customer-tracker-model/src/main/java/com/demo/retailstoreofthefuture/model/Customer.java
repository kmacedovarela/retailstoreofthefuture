package com.demo.retailstoreofthefuture.model;

import java.awt.Point;

public class Customer{
	private String customerId;
	private long timestamp;
	private Point location;
	private String departmentName;
	private Integer departmentVisitCount;
	private boolean messageSent;
	
	public Customer(String custId, String deptName, Integer deptVisitCount) {
		this.customerId = custId;
		this.departmentName = deptName;
		this.departmentVisitCount = deptVisitCount;
	}
	
	public Customer(String custId, long timestamp, Point location, String deptName) {
		super();
		this.customerId = custId;
		this.location = location;
		this.timestamp = timestamp;
		this.departmentName = deptName;
		this.messageSent = false;
	}
	

	public String getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public long getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public Point getLocation() {
		return this.location;
	}

	public void setLocation(Point location) {
		this.location = location;
	}

	public String getDepartmentName() {
		return this.departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public Integer getDepartmentVisitCount() {
		return this.departmentVisitCount;
	}

	public void setDepartmentVisitCount(Integer departmentVisitCount) {
		this.departmentVisitCount = departmentVisitCount;
	}

	public boolean isMessageSent() {
		return this.messageSent;
	}

	public boolean getMessageSent() {
		return this.messageSent;
	}

	public void setMessageSent(boolean messageSent) {
		this.messageSent = messageSent;
	}


}
