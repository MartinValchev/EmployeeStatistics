package com.ibm.cit.employeeStatsWeb.EmployeeStatsWeb.DAO;

import java.util.List;

import com.ibm.cit.employeeStatsWeb.EmployeeStatsWeb.model.Employee;

public class EmployeeDaoImpl implements EmployeeDao {

	@Override
	public Employee getEmployee(int id) {
		Employee employee = null;
		List<Employee> employeesList = getEmployeeList();
		if (id > 0) {
			employee = employeesList.get(id);
		}
		return employee;
	}

	@Override
	public List<Employee> getEmployeeList() {
		EmployeeFileReader employeeFileReader = new EmployeeFileReaderImpl();
		List<String> employeeStringRecords = employeeFileReader.readWholeEmployeeRecords();
		EmployeeList emplList = new EmployeeList();
		List<Employee> employeesList = emplList.generateEmployeeList(employeeStringRecords);
		return employeesList;
	}

}
