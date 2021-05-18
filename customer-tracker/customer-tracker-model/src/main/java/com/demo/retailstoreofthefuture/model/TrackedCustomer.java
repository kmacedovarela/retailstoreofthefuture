package com.demo.retailstoreofthefuture.model;

import java.awt.Point;

/**
 * TrackedCustomer
 */
public class TrackedCustomer {

    private final String customerId;

    private long moveTimestamp;

    private Point location;
    private String departmentName;

	private Integer departmentVisitCount;
	private Integer sameDepartmentVisitCount;
    private String lastVisitedDepartment;

	private boolean messageSent;
    private boolean isFocused;
    private boolean isBrowsing;

    public TrackedCustomer(String customerId) {
        this.customerId = customerId;           
    }

    // fluent getters and setters

    public TrackedCustomer moveTimestamp(long moveTimestamp) {
        setMoveTimestamp(moveTimestamp);
        return this;
    }

    public TrackedCustomer location(Point location) {
        setLocation(location);
        return this;
    }

    public TrackedCustomer departmentName(String departmentName) {
        setDepartmentName(departmentName);
        return this;
    }

    public TrackedCustomer departmentVisitCount(Integer departmentVisitCount) {
        setDepartmentVisitCount(departmentVisitCount);
        return this;
    }

    public TrackedCustomer sameDepartmentVisitCount(Integer sameDepartmentVisitCount) {
        setSameDepartmentVisitCount(sameDepartmentVisitCount);
        return this;
    }

    public TrackedCustomer lastVisitedDepartment(String lastVisitedDepartment) {
        setLastVisitedDepartment(lastVisitedDepartment);
        return this;
    }

    public TrackedCustomer messageSent(boolean messageSent) {
        setMessageSent(messageSent);
        return this;
    }


 
    // Getters and setters


    public String getCustomerId() {
        return this.customerId;
    }


    public long getMoveTimestamp() {
        return this.moveTimestamp;
    }

    public void setMoveTimestamp(long moveTimestamp) {
        this.moveTimestamp = moveTimestamp;
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

    public Integer getSameDepartmentVisitCount() {
        return this.sameDepartmentVisitCount;
    }

    public void setSameDepartmentVisitCount(Integer sameDepartmentVisitCount) {
        this.sameDepartmentVisitCount = sameDepartmentVisitCount;
    }

    public String getLastVisitedDepartment() {
        return this.lastVisitedDepartment;
    }

    public void setLastVisitedDepartment(String lastVisitedDepartment) {
        this.lastVisitedDepartment = lastVisitedDepartment;
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


    public boolean isIsFocused() {
        return this.isFocused;
    }

    public boolean getIsFocused() {
        return this.isFocused;
    }

    public void setIsFocused(boolean isFocused) {
        this.isFocused = isFocused;
    }

    public boolean isIsBrowsing() {
        return this.isBrowsing;
    }

    public boolean getIsBrowsing() {
        return this.isBrowsing;
    }

    public void setIsBrowsing(boolean isBrowsing) {
        this.isBrowsing = isBrowsing;
    }
 
}