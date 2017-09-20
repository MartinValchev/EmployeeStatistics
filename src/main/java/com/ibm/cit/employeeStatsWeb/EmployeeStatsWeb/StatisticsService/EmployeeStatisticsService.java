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
	 * return Employee instance by provided first name
	 * 
	 * @param String firstName
	 * @return Employee
	 */
	List<Employee> getEmpoyeeFirstName(String firstName);
	/**
	 * return Employee instances by provided last name
	 * 
	 * @param String lastName
	 * @return List<Employee>
	 */
	List<Employee> getEmpoyeeLastName(String lastName);
	/**
	 * return list  of Employee instances by provided employee age
	 * 
	 * @param int age
	 * @return List<Employee>
	 */
	List<Employee> getEmpoyeeAge(int age);
	/**
	 * return list  of Employee instances by provided employee length of service
	 * 
	 * @param double lengthOfService
	 * @return List<Employee>
	 */
	List<Employee> getEmpoyeeLengthOfService(double lengthOfService);
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
	/**
	 * add employee to the database return and return it.
	 * 
	 * @param Employee employee
	 * @return Employee employee
	 */
	Employee addEmployee(Employee employee);
	
	/**
	 * get portion of the employee list limited by employee id and limit countl
	 * 
	 * @param int lastId, int limit
	 * @return List<Employee>
	 */
	List<Employee> getPortionEmployeeList(int offset, int limit);
	int getEmployeePages(int pageLimit);
}
