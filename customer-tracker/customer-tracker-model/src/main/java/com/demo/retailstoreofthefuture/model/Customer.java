package com.demo.retailstoreofthefuture.model;

import java.awt.Point;

public class Customer{
	private String customerId;
	private long timestamp;
	private Point location;
	private String departmentName;

	public static class CustomerBuilder {

        private String customerId;
        private Point location;
        private String departmentName;
        private long moveTimestamp;
                
        public CustomerBuilder(String customerId) {
            this.customerId = customerId;
        }

        public CustomerBuilder atLocation(Point location){
            this.location = location;

            return this; 
        }

        public CustomerBuilder withMoveTimestamp(long moveTimestamp){
            this.moveTimestamp = moveTimestamp;

            return this; 
        }

        public CustomerBuilder atDepartment(String departmentName){
            this.departmentName = departmentName;

            return this; 
        }

        public Customer build(){       
            Customer customer = new Customer();  
            customer.customerId = this.customerId;
            customer.departmentName = this.departmentName;
            customer.location = this.location;
            customer.timestamp = this.moveTimestamp;

            return customer;
        }
    }

	public String getCustomerId() {
		return this.customerId;
	}

	public long getTimestamp() {
		return this.timestamp;
	}


	public Point getLocation() {
		return this.location;
	}

	public String getDepartmentName() {
		return this.departmentName;
	}


}
