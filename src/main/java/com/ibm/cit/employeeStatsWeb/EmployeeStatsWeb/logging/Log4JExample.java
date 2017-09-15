package com.ibm.cit.employeeStatsWeb.EmployeeStatsWeb.logging;

import org.apache.log4j.Logger;

public class Log4JExample {
	private static Logger log = Logger.getLogger(Log4JExample.class);

	
	public static void main(String[] args) {
		log.fatal("Fatal Error occured");
		log.error("Error occured");
		log.warn("test Warning");
		log.info("Test Message");
		log.debug("Hello debug message");
		log.trace(" ello trace message");
	}
	

}
