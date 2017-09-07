package com.ibm.cit.employeeStatsWeb.EmployeeStatsWeb.DAO;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.ibm.cit.employeeStatsWeb.EmployeeStatsWeb.model.Employee;

public class EmployeeDaoDBImpl implements EmployeeDao {

	@Override
	public Employee getEmployee(int id) {
		List<Employee> employeeList = getEmployeeList();
		Employee employee = employeeList.get(id);
		return employee;
	}

	@Override
	public List<Employee> getEmployeeList() {
		List<Employee> employeeList = null;
		// EmployeeJDBC employeeJDBC = new EmployeeJDBC();
		// Connection connection = employeeJDBC.startConnection();
		// if (connection != null) {
		// employeeList = employeeJDBC.generateEmployeeList(connection);
		employeeList = getHibernateEmployeeList();
		// }
		return employeeList;
	}

	private List<Employee> getHibernateEmployeeList() {
		Transaction tx = null;
		Session session = null;
		List<Employee> results = null;
		SessionFactory sf = null;
		try {
			sf =SessionFactoryGenerator.getSessionFactoryInstance();
			//session = sf.openSession();
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
			e.printStackTrace();
		} finally {
			//session.close();
		}
		return results;
	}

	public Employee addEmployee(Employee employee) {
		Transaction tx = null;
		Session session = null;
		SessionFactory sf = null;
		Employee currentEmpl = null;
		try {

			sf =SessionFactoryGenerator.getSessionFactoryInstance();
			//session = sf.openSession();
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
			e.printStackTrace();
		} finally {
			//session.close();
		}
		return currentEmpl;

	}
	private Employee getNewlyCreatedEmployee() {
		Transaction tx = null;
		Session session = null;
		SessionFactory sf = null;
		List<Employee> results = null;
		sf =SessionFactoryGenerator.getSessionFactoryInstance();
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

}
