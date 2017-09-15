package com.ibm.cit.employeeStatsWeb.EmployeeStatsWeb.DAO;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.ibm.cit.employeeStatsWeb.EmployeeStatsWeb.model.Employee;

public class EmployeeDaoDBImpl implements EmployeeDao {

	@Override
	public Employee getEmployee(int id) {
		Transaction tx = null;
		Session session = null;
		List<Employee> results = null;
		SessionFactory sf = null;
		try {
			sf = SessionFactoryGenerator.getSessionFactoryInstance();
			session = sf.getCurrentSession();
			tx = session.beginTransaction();
			String employeeQuerry = "SELECT * FROM Employee where id= " +id;
			SQLQuery query = session.createSQLQuery(employeeQuerry);
			query.addEntity(Employee.class);
			results = query.list();
			tx.commit();

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			String errorMessage =e.toString();
			addLogging(errorMessage);
		} finally {
			// session.close();
		}
		Employee employee =(results==null)?null:results.get(0);
		return employee;
	}

	@Override
	public List<Employee> getEmployeeList() {
		List<Employee> employeeList = null;
		employeeList = getHibernateEmployeeList();
		return employeeList;
	}

	private List<Employee> getHibernateEmployeeList() {
		Transaction tx = null;
		Session session = null;
		List<Employee> results = null;
		SessionFactory sf = null;
		try {
			sf = SessionFactoryGenerator.getSessionFactoryInstance();
			// session = sf.openSession();
			session = sf.getCurrentSession();
			tx = session.beginTransaction();
			String allContactsQuery = "select * FROM Employee";
			SQLQuery query = session.createSQLQuery(allContactsQuery);
			query.addEntity(Employee.class);
			results = query.list();
			tx.commit();

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			String errorMessage =e.toString();
			addLogging(errorMessage);
		} finally {
			// session.close();
		}
		return results;
	}

	public Employee addEmployee(Employee employee) {
		Transaction tx = null;
		Session session = null;
		SessionFactory sf = null;
		Employee currentEmpl = null;
		try {

			sf = SessionFactoryGenerator.getSessionFactoryInstance();
			// session = sf.openSession();
			session = sf.getCurrentSession();
			tx = session.beginTransaction();
			session.save(employee);
			if (!tx.wasCommitted()) {
				tx.commit();
			}
			currentEmpl = getNewlyCreatedEmployee();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			String errorMessage =e.toString();
			addLogging(errorMessage);
		} finally {
			// session.close();
		}
		return currentEmpl;

	}

	private Employee getNewlyCreatedEmployee() {
		Transaction tx = null;
		Session session = null;
		SessionFactory sf = null;
		List<Employee> results = null;
		sf = SessionFactoryGenerator.getSessionFactoryInstance();
		session = sf.getCurrentSession();
		tx = session.beginTransaction();
		String allContactsQuery = "SELECT * FROM Employee ORDER BY ID DESC limit 1";
		SQLQuery query = session.createSQLQuery(allContactsQuery);

		query.addEntity(Employee.class);
		results = query.list();
		if (!tx.wasCommitted()) {
			tx.commit();
		}

		return results.get(0);
	}
	private void addLogging(String message) {
		Logger log = Logger.getLogger(EmployeeDaoDBImpl.class);
		log.error(message);
	}
	@Override
	public List<Employee> getPortionEmployeeList(int offset, int limit) {
		Transaction tx = null;
		Session session = null;
		List<Employee> results = null;
		SessionFactory sf = null;
		try {
			sf = SessionFactoryGenerator.getSessionFactoryInstance();
			session = sf.getCurrentSession();
			tx = session.beginTransaction();
			String portionContactsQuery = "SELECT * FROM Employee ORDER BY id Asc OFFSET " + offset + " ROWS FETCH NEXT "
					+ limit + " ROWS ONLY";
			SQLQuery query = session.createSQLQuery(portionContactsQuery);
			query.addEntity(Employee.class);
			results = query.list();
			tx.commit();

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			String errorMessage =e.toString();
			addLogging(errorMessage);
		} finally {
			// session.close();
		}
		return results;
	}

	@Override
	public int getAllEmployeesCount() {
		Transaction tx = null;
		Session session = null;
		List<Object> results = null;
		SessionFactory sf = null;
		int employeeList = 0;
		try {
			sf = SessionFactoryGenerator.getSessionFactoryInstance();
			session = sf.getCurrentSession();
			tx = session.beginTransaction();
			String portionContactsQuery = "SELECT COUNT(*) FROM employee";
			SQLQuery query = session.createSQLQuery(portionContactsQuery);
			results = query.list();
			String countStr = results.get(0).toString();
			employeeList = Integer.parseInt(countStr);
			tx.commit();

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			String errorMessage =e.toString();
			addLogging(errorMessage);
		} finally {
			// session.close();
		}
		return employeeList;
	}

}
