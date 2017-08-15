package com.ibm.cit.employeeStatsWeb.EmployeeStatsWeb.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

//@XmlRootElement(name = "Employee")
@Entity
@Table(name = "Employee")
public class Employee {

	private String employeeFirstName;
	private String employeeLastName;
	private double age;
	private double lengthOfService;
	private int id;

	@Column(name = "first_name",nullable = false)
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

	@Id
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
