package com.ibm.cit.employeeStatsWeb.EmployeeStatsWeb.DAO;

import java.util.List;

import com.ibm.cit.employeeStatsWeb.EmployeeStatsWeb.model.Employee;


public interface EmployeeDao {
	/**
	 * get Employee entity using provided id
	 * 
	 * @param int id
	 * @throws @return
	 *             Employee
	 */
	public Employee getEmployee(int id);
	/**
	 * provides List of Employee instances based on employee length of service criteria
	 * 
	 * @param double lengthOfService
	 * @throws @return
	 *             List<Employee>
	 */
	public List<Employee> getEmployeeListLength(double lengthOfService);
	/**
	 * provides List of Employee instances based on employee age criteria
	 * 
	 * @param int age
	 * @throws @return
	 *             List<Employee>
	 */
	public List<Employee> getEmployeeListAge(int age);
	/**
	 * provides List of Employee instances based on employee first name,offset and elements limit criteria
	 * 
	 * @param String firstName
	 * @throws @return
	 *             List<Employee>
	 */
	public List<Employee> getEmployeeListFirstName(String firstName);
	/**
	 * provides List of Employee instances based on employee last name criteria
	 * 
	 * @param String lastName
	 * @throws @return
	 *             List<Employee>
	 */
	public List<Employee> getEmployeeListLastName(String lastName);
	/**
	 * provides List of Employee instances
	 * 
	 * @param
	 * @throws @return
	 *             List<Employee>
	 */
	public List<Employee> getEmployeeList();
	
	/**
	 * provides functionality to add employee to the database
	 * 
	 * @param Employee employee
	 * @throws @return
	 *             
	 */
	public Employee addEmployee(Employee employee );
	
	/**
	 * gets portion of all employee records table using provided offset and limit values
	 * 
	 * @param int offset, int limit
	 * @throws @return List<Employee>
	 *             
	 */
	public List<Employee> getPortionEmployeeList(int offset, int limit);
	
	/**
	 * provided all employees count stored in Employee table
	 * 
	 * @param 
	 * @throws @return int
	 *             
	 */
	public int getAllEmployeesCount();
}