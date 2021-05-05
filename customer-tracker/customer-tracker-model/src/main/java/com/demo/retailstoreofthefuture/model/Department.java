package com.demo.retailstoreofthefuture.model;

import java.awt.Rectangle;

public class Department {

	private String name;
	private Rectangle deptLocation;


	public Department() {
		super();		
	}

	public Department(String departmentName, Rectangle deptLocation) {
		super();
		
		this.name = departmentName;
		this.deptLocation = deptLocation;
		
	}

	public Department(
			Rectangle deptLocation) {
		super();
		this.deptLocation = deptLocation;
	}
	
	public Department(
			String departmentName) {
                
		super();
		this.name = departmentName;
	}
	
	public Rectangle getDeptLocation() {
		return deptLocation;
	}

	public void setDeptLocation(Rectangle deptLocation) {
		this.deptLocation = deptLocation;
	}
	
	// public boolean containsCustomer(CustomerObj c) {
	// 	if (this.deptLocation.contains(c.getLocation())) return true;
	// 	else return false;
	// }

	public String getDepartmentName() {
		return name;
	}

	public void setDepartmentName(String departmentName) {
		this.name = departmentName;
	}


}
