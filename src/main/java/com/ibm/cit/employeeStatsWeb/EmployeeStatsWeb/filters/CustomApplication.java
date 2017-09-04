package com.ibm.cit.employeeStatsWeb.EmployeeStatsWeb.filters;

import org.glassfish.jersey.server.ResourceConfig;

public class CustomApplication extends ResourceConfig{
	public CustomApplication() {
		packages("com.ibm.cit.employeeStatsWeb.EmployeeStatsWeb.filters");
		register(SecurityFilter.class);
		
	}
}
