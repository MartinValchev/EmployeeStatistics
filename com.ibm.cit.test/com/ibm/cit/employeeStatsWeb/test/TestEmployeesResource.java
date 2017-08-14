package com.ibm.cit.employeeStatsWeb.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

public class TestEmployeesResource {
	private static String allEmployeesUrl = "http://localhost:8080/EmployeeStatsWeb/webapi/employees";
	private static String employeesIdUrl = "http://localhost:8080/EmployeeStatsWeb/webapi/employees/1";
	private static String markerIdUrl = "http://localhost:8080/EmployeeStatsWeb/webapi/employees?markerId=6";
	private static String subListUrl = "http://localhost:8080/EmployeeStatsWeb/webapi/employees?start=3&size=2";

	public static void main(String[] args) {
		TestEmployeesResource testResource = new TestEmployeesResource();

		testResource.testHttpClient(allEmployeesUrl);
		testResource.testHttpClient(employeesIdUrl);
		testResource.testHttpClient(markerIdUrl);
		testResource.testHttpClient(subListUrl);

	}

	public void testHttpClient(String url) {
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet getEmployees = new HttpGet(url);
		try {
			HttpResponse httpResponce = client.execute(getEmployees);
			System.out.println("Response code: " + httpResponce.getStatusLine().getStatusCode());
			BufferedReader rd = new BufferedReader(new InputStreamReader(httpResponce.getEntity().getContent()));
			String line = "";
			while ((line = rd.readLine()) != null) {
				System.out.println(line);
			}

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			getEmployees.releaseConnection();
		}
	}

}
