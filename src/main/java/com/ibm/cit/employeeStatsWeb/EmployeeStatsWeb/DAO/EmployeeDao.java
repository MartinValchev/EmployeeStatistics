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
}