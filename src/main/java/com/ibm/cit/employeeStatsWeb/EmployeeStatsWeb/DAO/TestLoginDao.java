package com.ibm.cit.employeeStatsWeb.EmployeeStatsWeb.DAO;

import com.ibm.cit.employeeStatsWeb.EmployeeStatsWeb.model.Employee;

public class TestLoginDao {
	public static void main(String[] args) {
		EmployeeDao emplDao = new EmployeeDaoDBImpl();
		Employee employee = emplDao.getEmployee(5);
		System.out.println(employee.getEmployeeFirstName());
		Employee employee2 = emplDao.getEmployee(10);
		System.out.println(employee2.getEmployeeLastName());
	}
}
