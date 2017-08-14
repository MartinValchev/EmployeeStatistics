package com.ibm.cit.employeeStatsWeb.EmployeeStatsWeb.DAO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.ibm.cit.employeeStatsWeb.EmployeeStatsWeb.StatisticsService.Constants;



public class EmployeeFileReaderImpl implements EmployeeFileReader {
	private File inputFile;

	public EmployeeFileReaderImpl() {
		inputFile = new File(Constants.INPUT_FILE_LOCATION);
	}

	public List<String> readWholeEmployeeRecords() {
		List<String> employeeRecords = new ArrayList<String>();
		StringBuilder builder = new StringBuilder();
		BufferedReader bufferedReader =null;
		try {
			bufferedReader = new BufferedReader(new FileReader(inputFile));
			String currentLine = null;
			while ((currentLine = bufferedReader.readLine()) != null) {

				if (currentLine.contains(Constants.EMPLOYEE_RECORD_SEPARATOR) && builder.length() > 0) {
					employeeRecords.add(builder.toString());
					builder.setLength(0);
					continue;
				}

				builder.append(currentLine);
				builder.append("\n");

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			closeBufferedReader(bufferedReader);
		}
		return employeeRecords;
	}

	private void closeBufferedReader(BufferedReader bufferedReader) {
		try {
			if (bufferedReader != null) {
				bufferedReader.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String readWholeInputFile() {
		StringBuilder result = new StringBuilder();
		BufferedReader bufferedReader =null;
		try {
			bufferedReader = new BufferedReader(new FileReader(inputFile));
			String line = "";
			while ((line = bufferedReader.readLine()) != null) {
				result.append(line);
				result.append("\n");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			closeBufferedReader(bufferedReader);
		}
		return result.toString().trim();
	}

}
