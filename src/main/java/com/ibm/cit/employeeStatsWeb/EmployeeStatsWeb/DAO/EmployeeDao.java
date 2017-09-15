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