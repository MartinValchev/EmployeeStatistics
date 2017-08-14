package com.ibm.cit.employeeStatsWeb.EmployeeStatsWeb.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Employee")
public class Employee {

	private String employeeFirstName;
	private String employeeLastName;
	private double age;
	private double lengthOfService;
	private int id;

	public String getEmployeeFirstName() {
		return employeeFirstName;
	}

	public void setEmployeeFirstName(String employeeFirstName) {
		this.employeeFirstName = employeeFirstName;
	}

	public String getEmployeeLastName() {
		return employeeLastName;
	}

	public void setEmployeeLastName(String employeeLastName) {
		this.employeeLastName = employeeLastName;
	}

	public double getAge() {
		return age;
	}

	public void setAge(double age) {
		this.age = age;
	}

	public double getLengthOfService() {
		return lengthOfService;
	}

	public void setLengthOfService(double lengthOfService) {
		this.lengthOfService = lengthOfService;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
