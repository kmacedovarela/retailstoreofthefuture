package com.demo.retailstoreofthefuture.model;

/**
 * TrackedCustomer
 */
public class TrackedCustomer {

    private String customerId;
	private Department department;
	private Integer visitCount;
	private boolean messageSent;
    
	public TrackedCustomer() {}
	
    public TrackedCustomer(String customerId, Department department) {
		super();
		this.customerId = customerId;
		this.department = department;
	}

	public String getCustomerId() {
        return customerId;
    }
    public void setCustomerId( String customerId) {
        this.customerId = customerId;
    }
    public Department getDepartment() {
        return department;
    }
    public void setDepartment(Department department) {
        this.department = department;
    }
    public Integer getVisitCount() {
        return visitCount;
    }
    public void setVisitCount(Integer visitCount) {
        this.visitCount = visitCount;
    }
    public boolean isMessageSent() {
        return messageSent;
    }
    public void setMessageSent(boolean messageSent) {
        this.messageSent = messageSent;
    }



}