package com.ibm.cit.employeeStatsWeb.EmployeeStatsWeb.DAO;

import java.util.ArrayList;
import java.util.List;

import com.ibm.cit.employeeStatsWeb.EmployeeStatsWeb.model.Employee;

public class EmployeeList {

	private Employee getEmployeeEntry(String employeeRecord) {
		Employee employeeEntry = null;
		if (employeeRecord.contains("\n")) {
			String[] employeeInfos = employeeRecord.split("\n");
			String idString = employeeInfos[0].substring(employeeInfos[0].indexOf('=') + 1);
			String fullNameStr = employeeInfos[1].substring(employeeInfos[1].indexOf('=') + 1).trim();
			String ageStr = employeeInfos[2].substring(employeeInfos[2].indexOf('=') + 1).trim();
			String lengthOfServiceStr = employeeInfos[3].substring(employeeInfos[3].indexOf('=') + 1).trim();
			employeeEntry = new Employee();
			int id = Integer.parseInt(idString);
			String firstName = fullNameStr.substring(0, fullNameStr.indexOf(' ')).trim();
			employeeEntry.setEmployeeFirstName(firstName);
			String lastName = fullNameStr.substring(fullNameStr.indexOf(' ') + 1).trim();
			employeeEntry.setEmployeeLastName(lastName);
			double age = Double.parseDouble(ageStr);
			employeeEntry.setAge(age);
			employeeEntry.setLengthOfService(Double.parseDouble(lengthOfServiceStr));
			employeeEntry.setId(id);
		}
		return employeeEntry;

	}

	public List<Employee> generateEmployeeList(List<String> employeeRecords) {
		List<Employee> employeeList = null;
		if (employeeRecords.size() > 0) {
			employeeList = new ArrayList<>();
			for (String employeeRecord : employeeRecords) {
				Employee employee = getEmployeeEntry(employeeRecord);
				employeeList.add(employee);
			}
		}
		return employeeList;

	}
}
