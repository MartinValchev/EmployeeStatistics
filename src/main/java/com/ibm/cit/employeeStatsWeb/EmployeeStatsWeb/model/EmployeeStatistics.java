package com.ibm.cit.employeeStatsWeb.EmployeeStatsWeb.model;

public class EmployeeStatistics {

	private double employeeAvgAge;
	private String mostCommonChars;
	private double avgLengthOfService;
	private double maxLengthOfService;
	private StringBuilder builder;

	public EmployeeStatistics() {
		builder = new StringBuilder();
	}

	public double getEmployeeAvgAge() {
		return employeeAvgAge;

	}

	public void setEmployeeAvgAge(double employeeAvgAge) {
		this.employeeAvgAge = employeeAvgAge;
	}

	public String getMostCommonChars() {
		return mostCommonChars;
	}

	public void setMostCommonChars(String mostCommonChars) {
		this.mostCommonChars = mostCommonChars;
	}

	public double getAvgLengthOfService() {
		return avgLengthOfService;
	}

	public void setAvgLengthOfService(double avgLengthOfService) {
		this.avgLengthOfService = avgLengthOfService;
	}

	public double getMaxLengthOfService() {
		return maxLengthOfService;
	}

	public void setMaxLengthOfService(double maxLengthOfService) {
		this.maxLengthOfService = maxLengthOfService;
	}

	@Override
	public String toString() {

		builder.setLength(0);
		builder.append("Average age of the employees: ").append(String.format("%.2f", getEmployeeAvgAge()))
				.append("\r\n");
		builder.append("The first three most common characters in the names of the employees: ")
				.append(getMostCommonChars()).append("\r\n");
		builder.append("The average length of service of the employees: ")
				.append(String.format("%.2f", getAvgLengthOfService())).append(" years").append("\r\n");
		builder.append("The maximum length of service among all employees: ").append(getMaxLengthOfService())
				.append(" years").append("\r\n");
		return builder.toString();
	}
}
