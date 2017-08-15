package com.ibm.cit.employeeStatsWeb.EmployeeStatsWeb.DAO;

import java.sql.Connection;
import java.util.List;

import com.ibm.cit.employeeStatsWeb.EmployeeStatsWeb.model.Employee;

public class EmployeeDaoDBImpl implements EmployeeDao {

	@Override
	public Employee getEmployee(int id) {
		List<Employee> employeeList = getEmployeeList();
		Employee employee = employeeList.get(id);
		return employee;
	}

	@Override
	public List<Employee> getEmployeeList() {
		List<Employee> employeeList = null;
		EmployeeJDBC employeeJDBC = new EmployeeJDBC();
		Connection connection = employeeJDBC.startConnection();
		if (connection != null) {
			employeeList = employeeJDBC.generateEmployeeList(connection);
			employeeJDBC.closeConnection(connection);
		}
		return employeeList;
	}

}
