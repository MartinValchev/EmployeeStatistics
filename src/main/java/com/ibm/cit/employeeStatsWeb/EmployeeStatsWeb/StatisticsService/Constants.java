package com.ibm.cit.employeeStatsWeb.EmployeeStatsWeb.StatisticsService;

public class Constants {
	public static final String INPUT_FILE_LOCATION = "C:\\Users\\martin.valchev\\projects\\EmployeeStatisticsWeb\\InputFileTest.txt";
	public static final String EMPLOYEE_RECORD_SEPARATOR = "<<>>";
	public static final String INVALID_EMPLOYEE_RECORD_MESSAGE = "Invalid Employee Records provided";
	public static final String INVALID_INPUT_FILE = "Invalid Input File provided";
	public static final String INVALID_FILE_CONTENT = "Invalid File content provided";
	public static final String EMPLOYEE_VALIDATION_REGEX = "((name\\=(\\w+\\s)+age\\=[0-9]+\nlengthOfService\\=[0-9]+.?[0-9]?)\n?(<<>>?))[\n]?";

	public static void printMessage(String input) {
		System.out.println(input);
	}

	public static void printErrorMessage(String errorInput) {
		System.err.println(errorInput);
	}
}
