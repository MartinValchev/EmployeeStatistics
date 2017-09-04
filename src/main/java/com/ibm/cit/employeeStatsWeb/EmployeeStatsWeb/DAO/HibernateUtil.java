package com.ibm.cit.employeeStatsWeb.EmployeeStatsWeb.DAO;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	public static final SessionFactory sessionFactory = buildSessionFactory();

	public static SessionFactory buildSessionFactory() {
		try {
			// Create the SessionFactory from hibernate.cfg.xml

			
			return new Configuration().configure(HibernateUtil.class.getResource("/hibernate.cfg.xml")).buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
		
	}
	public static void shutDown() {
		getSessionFactory().close();
	}
}
