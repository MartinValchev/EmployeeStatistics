package com.ibm.cit.employeeStatsWeb.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

public class TestEmployeeStatistics {
	public static void main(String[] args) {
		TestEmployeeStatistics statistics = new TestEmployeeStatistics();
		statistics.testGetEmployeeStats();
	}

	public void testGetEmployeeStats() {
		String url = "http://localhost:8080/EmployeeStatsWeb/webapi/statistics";
		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpGet httpGet = new HttpGet(url);
		try {
			HttpResponse httpResponse = httpClient.execute(httpGet);
			System.out.println("Response code: " + httpResponse.getStatusLine().getStatusCode());
			BufferedReader reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
			String line = "";
			StringBuilder stringBuilder = new StringBuilder();
			while ((line = reader.readLine()) != null) {
				stringBuilder.append(line);
			}
			System.out.println(stringBuilder.toString());
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			httpGet.releaseConnection();
		}
	}
}
