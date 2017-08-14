package com.ibm.cit.employeeStatsWeb.EmployeeStatsWeb.StatisticsService;

import java.util.List;

import com.ibm.cit.employeeStatsWeb.EmployeeStatsWeb.DAO.EmployeeDao;
import com.ibm.cit.employeeStatsWeb.EmployeeStatsWeb.DAO.EmployeeDaoImpl;
import com.ibm.cit.employeeStatsWeb.EmployeeStatsWeb.model.Employee;
import com.ibm.cit.employeeStatsWeb.EmployeeStatsWeb.model.EmployeeStatistics;

public class EmployeeStatisticsServiceImpl implements EmployeeStatisticsService {

	public EmployeeStatistics generateNewEmployeesStatistics() {
		EmployeeDao employeeDao = new EmployeeDaoImpl();
		List<Employee> employeeeList = employeeDao.getEmployeeList();
		Calculations calculations = new CalculationsImpl(employeeeList);
		EmployeeStatistics empoyeeStatistics = new EmployeeStatistics();
		double avgLengthOfService = calculations.calculateAvgLenghtOfService();
		double employeeAvgAge = calculations.calculateAvgEmpAge();
		double maxLengthOfService = calculations.findMaxLengthOfService();
		String mostCommonChars = calculations.getThreeMostCommonChars();
		empoyeeStatistics.setEmployeeAvgAge(employeeAvgAge);
		empoyeeStatistics.setMostCommonChars(mostCommonChars);
		empoyeeStatistics.setAvgLengthOfService(avgLengthOfService);
		empoyeeStatistics.setMaxLengthOfService(maxLengthOfService);
		
		return empoyeeStatistics;
	}

	@Override
	public Employee getEmpoyee(int employeeID) {
		EmployeeDao employeeDao = new EmployeeDaoImpl();
		List<Employee> employeeeList = employeeDao.getEmployeeList();
		Employee employee = null;
		for(Employee empl: employeeeList) {
			if(empl.getId() ==employeeID) {
				employee = empl;
				break;
			}
			
		}
		return employee;
	}
	@Override
	public List<Employee> getEmployees() {
		EmployeeDao employeeDao = new EmployeeDaoImpl();
		List<Employee> employeeeList = employeeDao.getEmployeeList();
		return employeeeList;
	}

	@Override
	public int getEmployeeListSize() {
		
		return getEmployees().size();
	}

}
