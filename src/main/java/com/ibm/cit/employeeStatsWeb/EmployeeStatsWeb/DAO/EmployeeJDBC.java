package com.ibm.cit.employeeStatsWeb.EmployeeStatsWeb.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ibm.cit.employeeStatsWeb.EmployeeStatsWeb.model.Employee;

public class EmployeeJDBC {
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "org.postgresql.Driver";
	static final String DB_URL = "jdbc:postgresql://127.0.0.1:5432/EmployeeDatabase";
	// Database credentials
	static final String USER = "postgres";
	static final String PASS = "Postgre12@";

	public Connection startConnection() {

		Connection connection = null;
		try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(DB_URL, USER, PASS);

		} catch (SQLException e) {

			System.out.println("Connection Failed! Check output console");
			System.out.println(e.getMessage());
			return connection;
		} catch (ClassNotFoundException e) {

			System.out.println("Where is your PostgreSQL JDBC Driver? " + "Include in your library path!");
			e.printStackTrace();
			return null;

		} finally {
			closeConnection(connection);
		}
		return connection;
	}

	public List<Employee> generateEmployeeList(Connection connection) {
		String employeesSQL = "SELECT * from public.\"Employee\"";
		List<Employee> employees = null;
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(employeesSQL);
			employees = new ArrayList<>();
			while (rs.next()) {
				int id = rs.getInt("id");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				double age = rs.getDouble("age");
				double lengthOfService = rs.getDouble("length_of_service");
				Employee currentEmployee = new Employee();
				currentEmployee.setAge(age);
				currentEmployee.setEmployeeFirstName(firstName);
				currentEmployee.setEmployeeLastName(lastName);
				currentEmployee.setId(id);
				currentEmployee.setLengthOfService(lengthOfService);
				employees.add(currentEmployee);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return employees;

	}

	public void closeConnection(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
