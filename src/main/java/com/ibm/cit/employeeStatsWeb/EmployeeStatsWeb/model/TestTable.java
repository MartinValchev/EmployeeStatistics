package com.ibm.cit.employeeStatsWeb.EmployeeStatsWeb.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "TestTable")
public class TestTable {
	private String nameTest;

	@Column(name = "nameTest")
	public String getNameTest() {
		return nameTest;
	}

	public void setNameTest(String nameTest) {
		this.nameTest = nameTest;
	}
}
