package com.ibm.cit.employeeStatsWeb.EmployeeStatsWeb.DAO;

import java.io.IOException;
import java.util.List;

public interface EmployeeFileReader {

	/**
	 * process the whole input file by reading Employee Record one by one. Each
	 * Employee Record is added to employee list which is returned.Employee
	 * Separator (<<>>) is not included in the reading.
	 * 
	 * @param
	 * @return List<String>
	 */
	List<String> readWholeEmployeeRecords();

	/**
	 * read the whole document line by line (Including Employee line separator)
	 * and returns it as a String
	 * 
	 * 
	 * @param BufferedReader
	 *            bufferedReader,String line
	 * @throws IOException
	 * @return String
	 */

	String readWholeInputFile();
}
