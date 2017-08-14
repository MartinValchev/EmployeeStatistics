package com.ibm.cit.employeeStatsWeb.EmployeeStatsWeb.StatisticsService;

public interface Calculations {
	/**
	 * Calculates the Average Employee Age among all employee Records
	 * 
	 * @param
	 * @throws @return
	 *             double
	 */
	double calculateAvgEmpAge();
	/**
	 * Calculates the Average Length Of Service of all employee Records
	 * 
	 * @param
	 * @throws @return
	 *             double
	 */
	double calculateAvgLenghtOfService();

	/**
	 * Finds the maximum  Length Of Service amoing all employee Records
	 * 
	 * @param
	 * @throws @return
	 *             double
	 */
	double findMaxLengthOfService();

	/**
	 * returns the three most common characters that appear in employee names. Case insensitive
	 * 
	 * @param
	 * @throws @return
	 *             double
	 */
	String getThreeMostCommonChars();
}
