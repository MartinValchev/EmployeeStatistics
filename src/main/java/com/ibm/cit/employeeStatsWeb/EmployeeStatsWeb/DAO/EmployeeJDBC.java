package com.ibm.cit.employeeStatsWeb.EmployeeStatsWeb.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EmployeeJDBC {
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "org.postgresql.Driver";
	static final String DB_URL = "jdbc:postgresql://127.0.0.1:5432/EmployeeDatabase";

	// Database credentials
	static final String USER = "postgres";
	static final String PASS = "Postgre12@";

	public void startConnection() {

		Connection connection = null;
		Statement stmt = null;
		try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
			String sql = "SELECT * from public.\"Employee\"";
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int id = rs.getInt("id");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				double age = rs.getDouble("age");
				double lengthOfService = rs.getDouble("length_of_service");
				System.out.println("ID: " + id);
				System.out.println("first name: " + firstName);
				System.out.println("last name: " + lastName);
				System.out.println("Age: " + age);
				System.out.println("Length Of Service:" + lengthOfService);
			}

		} catch (SQLException e) {

			System.out.println("Connection Failed! Check output console");
			System.out.println(e.getMessage());
			return;
		} catch (ClassNotFoundException e) {

			System.out.println("Where is your PostgreSQL JDBC Driver? " + "Include in your library path!");
			e.printStackTrace();
			return;

		}

		closeConnection(connection);
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
