package com.ibm.cit.employeeStatsWeb.EmployeeStatsWeb.DAO;

import org.hibernate.SessionFactory;
import org.hibernate.Session;

public class HibernateSessionExample {

	public static void main(String[] args) {

		SessionFactory sf = SessionFactoryGenerator.getSessionFactoryInstance();

		// Current Session - no need to close
		Session currentSession = sf.getCurrentSession();

		// open new session
		Session newSession = sf.openSession();
		// perform db operations

		// close session
		newSession.close();

	}
}