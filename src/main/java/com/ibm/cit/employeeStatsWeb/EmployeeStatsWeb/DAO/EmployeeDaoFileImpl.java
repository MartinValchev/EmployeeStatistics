package com.ibm.cit.employeeStatsWeb.EmployeeStatsWeb.DAO;

import java.util.List;

import com.ibm.cit.employeeStatsWeb.EmployeeStatsWeb.model.Employee;

public class EmployeeDaoFileImpl implements EmployeeDao {

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

	@Override
	public Employee addEmployee(Employee employee) {
		return null;
		
	}

	@Override
	public List<Employee> getPortionEmployeeList(int lastId, int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getAllEmployeesCount() {
		// TODO Auto-generated method stub
		return 0;
	}

}
