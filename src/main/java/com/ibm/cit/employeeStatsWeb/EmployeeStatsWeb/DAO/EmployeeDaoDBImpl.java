package com.ibm.cit.employeeStatsWeb.EmployeeStatsWeb.DAO;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

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
		//EmployeeJDBC employeeJDBC = new EmployeeJDBC();
		//Connection connection = employeeJDBC.startConnection();
		//if (connection != null) {
			//employeeList = employeeJDBC.generateEmployeeList(connection);
			employeeList = getHibernateEmployeeList();
		//}
		return employeeList;
	}
	private List<Employee> getHibernateEmployeeList(){
		Transaction tx = null;
		Session session = null;
		List<Employee> results =null;
		try {
			SessionFactory sf = new Configuration().configure("/hibernate.cfg.xml").buildSessionFactory();
			session = sf.openSession();
			tx = session.beginTransaction();
			String allContactsQuery = "select * FROM Employee";
			SQLQuery query = session.createSQLQuery(allContactsQuery);
			query.addEntity(Employee.class);
			results =  query.list();
			tx.commit();
			

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		      return results;
	}
	
}
