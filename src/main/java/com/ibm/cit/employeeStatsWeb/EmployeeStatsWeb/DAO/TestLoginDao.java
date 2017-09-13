package com.ibm.cit.employeeStatsWeb.EmployeeStatsWeb.DAO;

import java.util.List;

import com.ibm.cit.employeeStatsWeb.EmployeeStatsWeb.model.Employee;

public class TestLoginDao {
	public static void main(String[] args) {
		EmployeeDao  employeeDao = new EmployeeDaoDBImpl();
		List<Employee> listEmpl = employeeDao.getPortionEmployeeList(0, 4);
	}
}
