package com.ibm.cit.employeeStatsWeb.EmployeeStatsWeb.StatisticsService;

import java.util.List;

import com.ibm.cit.employeeStatsWeb.EmployeeStatsWeb.model.Employee;
import com.ibm.cit.employeeStatsWeb.EmployeeStatsWeb.model.EmployeeStatistics;

public interface EmployeeStatisticsService {
	/**
	 * calculate and return Employee statistics 
	 * 	including employee Average Age;
	  3 most Common Chars in employees' names
		average length of service;
		maximum length of Service;
	 * 
	 * @param
	 * @return EmployeeStatistics
	 */
	EmployeeStatistics generateNewEmployeesStatistics();

	/**
	 * return Employee instance by provided employeeId
	 * 
	 * @param int employeeID
	 * @return Employee
	 */
	Employee getEmpoyee(int employeeID);
	
	/**
	 * return List of Employee instances.
	 * 
	 * @param
	 * @return List<Employee>
	 */
	List<Employee> getEmployees();
	
	/**
	 * return the size of List of Employee instances.
	 * 
	 * @param
	 * @return int employeeSize
	 */
	int getEmployeeListSize();
}
