package com.ibm.cit.employeeStatsWeb.EmployeeStatsWeb.DAO;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.ibm.cit.employeeStatsWeb.EmployeeStatsWeb.model.Employee;


public class HibernateConnection {
	public static void main(String[] args) {
		addEmployee();
	}

	
	public void connect() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		String employeesSQL = "SELECT * from Employee";
		Query employeesQuery = session.createSQLQuery(employeesSQL);
		List<Employee> employees= employeesQuery.list();
	}
	public static void addEmployee(){
		Transaction tx = null;
		Session session = null;
		try {
			SessionFactory sf = new Configuration().configure("/hibernate.cfg.xml").buildSessionFactory();
			session = sf.openSession();
			tx = session.beginTransaction();
		Employee employee = new Employee();
		employee.setEmployeeFirstName("Ivan");
		employee.setEmployeeLastName("Ivanov");
		employee.setAge(45.0);
		employee.setLengthOfService(6.7);

		session.save(employee);
		session.getTransaction().commit();

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
}
