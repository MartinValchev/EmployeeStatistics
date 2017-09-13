package com.ibm.cit.employeeStatsWeb.EmployeeStatsWeb.StatisticsService;

import java.util.List;

import com.ibm.cit.employeeStatsWeb.EmployeeStatsWeb.DAO.EmployeeDao;
import com.ibm.cit.employeeStatsWeb.EmployeeStatsWeb.DAO.EmployeeDaoDBImpl;
import com.ibm.cit.employeeStatsWeb.EmployeeStatsWeb.model.Employee;
import com.ibm.cit.employeeStatsWeb.EmployeeStatsWeb.model.EmployeeStatistics;

public class EmployeeStatisticsServiceImpl implements EmployeeStatisticsService {

	public EmployeeStatistics generateNewEmployeesStatistics() {
		EmployeeDao employeeDao = new EmployeeDaoDBImpl();
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
		EmployeeDao employeeDao = new EmployeeDaoDBImpl();
		Employee employee = employeeDao.getEmployee(employeeID);
		return employee;
	}
	@Override
	public List<Employee> getEmployees() {
		EmployeeDao employeeDao = new EmployeeDaoDBImpl();
		List<Employee> employeeeList = employeeDao.getEmployeeList();
		return employeeeList;
	}

	@Override
	public int getEmployeeListSize() {
		EmployeeDao employeeDao = new EmployeeDaoDBImpl();
		return employeeDao.getAllEmployeesCount();
	}

	@Override
	public Employee addEmployee(Employee employee) {
		EmployeeDao employeeDao = new EmployeeDaoDBImpl();
		Employee empl = employeeDao.addEmployee(employee);
		return empl;
	}

	@Override
	public List<Employee> getPortionEmployeeList(int offset, int limit) {
		EmployeeDao employeeDao = new EmployeeDaoDBImpl();
		List<Employee> employees = employeeDao.getPortionEmployeeList(offset, limit);
		return employees;
	}

	@Override
	public int getEmployeePages(int pageLimit) {
		int pagesCount = 0;
		EmployeeDao employeeDao = new EmployeeDaoDBImpl();
		int employeeCount = employeeDao.getAllEmployeesCount();
		if(employeeCount% pageLimit !=0) {
			pagesCount = employeeCount/pageLimit +1;
		}else {
			pagesCount = employeeCount/pageLimit;
		}
		
		return pagesCount;
	}


}
