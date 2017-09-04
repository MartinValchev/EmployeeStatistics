package com.ibm.cit.employeeStatsWeb.EmployeeStatsWeb.DAO;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionFactoryGenerator{
	private static SessionFactory instance = null;
	private SessionFactoryGenerator() {};

	public static SessionFactory getSessionFactoryInstance() {
		if(instance==null) {
			instance = new Configuration().configure("/hibernate.cfg.xml").buildSessionFactory();
		}
		return instance;
	}

}
