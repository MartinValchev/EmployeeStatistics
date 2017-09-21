package com.ibm.cit.employeeStatsWeb.EmployeeStatsWeb.DAO;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ibm.cit.employeeStatsWeb.EmployeeStatsWeb.model.Employee;

public class EmployeeDaoDBImpl implements EmployeeDao {

	@SuppressWarnings("unchecked")
	@Override
	public Employee getEmployee(int id) {
		Transaction tx = null;
		Session session = null;
		List<Employee> results = null;
		try {
			session = SessionFactoryGenerator.getSessionFactory().openSession();
			tx = session.beginTransaction();
			String employeeQuerry = "SELECT * FROM Employee where id= " + id;
			SQLQuery query = session.createSQLQuery(employeeQuerry);
			query.addEntity(Employee.class);
			results = query.list();
			tx.commit();

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			String errorMessage = e.toString();
			addLogging(errorMessage);
			SessionFactoryGenerator.shutdown();
		}
		Employee employee = (results == null) ? null : results.get(0);
		return employee;
	}

	@Override
	public List<Employee> getEmployeeList() {
		List<Employee> employeeList = null;
		employeeList = getHibernateEmployeeList();
		return employeeList;
	}

	@SuppressWarnings("unchecked")
	private List<Employee> getHibernateEmployeeList() {
		Transaction tx = null;
		Session session = null;
		List<Employee> results = null;
		try {
			session = SessionFactoryGenerator.getSessionFactory().openSession();
			tx = session.beginTransaction();
			String allContactsQuery = "select * FROM Employee";
			SQLQuery query = session.createSQLQuery(allContactsQuery);
			query.addEntity(Employee.class);
			results = query.list();
			tx.commit();

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			String errorMessage = e.toString();
			addLogging(errorMessage);
			SessionFactoryGenerator.shutdown();
		}
		return results;
	}

	public Employee addEmployee(Employee employee) {
		Transaction tx = null;
		Session session = null;
		Employee currentEmpl = null;
		try {

			session = SessionFactoryGenerator.getSessionFactory().openSession();
			tx = session.beginTransaction();
			session.save(employee);
			if (!tx.wasCommitted()) {
				tx.commit();
			}
			currentEmpl = getNewlyCreatedEmployee();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			String errorMessage = e.toString();
			addLogging(errorMessage);
			SessionFactoryGenerator.shutdown();
		}
		return currentEmpl;

	}

	@SuppressWarnings("unchecked")
	private Employee getNewlyCreatedEmployee() {
		Transaction tx = null;
		Session session = null;
		List<Employee> results = null;
		try {

			session = SessionFactoryGenerator.getSessionFactory().openSession();
			tx = session.beginTransaction();
			String allContactsQuery = "SELECT * FROM Employee ORDER BY ID DESC limit 1";
			SQLQuery query = session.createSQLQuery(allContactsQuery);

			query.addEntity(Employee.class);
			results = query.list();
		} catch (HibernateException e) {
			if (!tx.wasCommitted()) {
				tx.commit();
				String errorMessage = e.toString();
				addLogging(errorMessage);
				SessionFactoryGenerator.shutdown();
			}
		}
		return results.get(0);
	}

	private void addLogging(String message) {
		Logger log = Logger.getLogger(EmployeeDaoDBImpl.class);
		log.error(message);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> getPortionEmployeeList(int offset, int limit) {
		Transaction tx = null;
		Session session = null;
		List<Employee> results = null;
		try {
			session = SessionFactoryGenerator.getSessionFactory().openSession();
			tx = session.beginTransaction();
			String portionContactsQuery = "SELECT * FROM Employee ORDER BY id Asc OFFSET " + offset
					+ " ROWS FETCH NEXT " + limit + " ROWS ONLY";
			SQLQuery query = session.createSQLQuery(portionContactsQuery);
			query.addEntity(Employee.class);
			results = query.list();
			tx.commit();

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			String errorMessage = e.toString();
			addLogging(errorMessage);
			SessionFactoryGenerator.shutdown();
		}
		return results;
	}

	@SuppressWarnings("unchecked")
	@Override
	public int getAllEmployeesCount() {
		Transaction tx = null;
		Session session = null;
		List<Object> results = null;
		int employeeList = 0;
		try {
			session = SessionFactoryGenerator.getSessionFactory().openSession();
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
			String errorMessage = e.toString();
			addLogging(errorMessage);
			SessionFactoryGenerator.shutdown();
		}
		return employeeList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> getEmployeeListLength(double lengthOfService) {
		Transaction tx = null;
		Session session = null;
		List<Employee> results = null;
		try {
			session = SessionFactoryGenerator.getSessionFactory().openSession();
			tx = session.beginTransaction();
			String employeeQuerry = "SELECT * FROM Employee where length_of_service =" + lengthOfService;
			SQLQuery query = session.createSQLQuery(employeeQuerry);
			query.addEntity(Employee.class);
			results = query.list();
			tx.commit();

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			String errorMessage = e.toString();
			addLogging(errorMessage);
			SessionFactoryGenerator.shutdown();
		}
		return results;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> getEmployeeListAge(int age) {
		Transaction tx = null;
		Session session = null;
		List<Employee> results = null;
		try {
			session = SessionFactoryGenerator.getSessionFactory().openSession();
			tx = session.beginTransaction();
			String employeeQuerry = "SELECT * FROM Employee where age =" + age;
			SQLQuery query = session.createSQLQuery(employeeQuerry);
			query.addEntity(Employee.class);
			results = query.list();
			tx.commit();

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			String errorMessage = e.toString();
			addLogging(errorMessage);
			SessionFactoryGenerator.shutdown();
		} 
		return results;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> getEmployeeListFirstName(String firstName) {
		Transaction tx = null;
		Session session = null;
		List<Employee> results = null;
		try {
			session = SessionFactoryGenerator.getSessionFactory().openSession();
			tx = session.beginTransaction();
			String employeeQuerry = "SELECT * FROM Employee where first_name like '" + firstName + "%'";
			SQLQuery query = session.createSQLQuery(employeeQuerry);
			query.addEntity(Employee.class);
			results = query.list();
			tx.commit();

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			String errorMessage = e.toString();
			addLogging(errorMessage);
			SessionFactoryGenerator.shutdown();
		}
		return results;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> getEmployeeListLastName(String lastName) {
		Transaction tx = null;
		Session session = null;
		List<Employee> results = null;
		try {
			session = SessionFactoryGenerator.getSessionFactory().openSession();
			tx = session.beginTransaction();
			String employeeQuerry = "SELECT * FROM Employee where last_name like '" + lastName + "%'";
			SQLQuery query = session.createSQLQuery(employeeQuerry);
			query.addEntity(Employee.class);
			results = query.list();
			tx.commit();

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			String errorMessage = e.toString();
			addLogging(errorMessage);
			SessionFactoryGenerator.shutdown();
		} 
		return results;
	}

}
