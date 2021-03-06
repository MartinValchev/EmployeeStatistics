package com.ibm.cit.employeeStatsWeb.EmployeeStatsWeb.DAO;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ibm.cit.employeeStatsWeb.EmployeeStatsWeb.model.Login;
import com.ibm.cit.employeeStatsWeb.EmployeeStatsWeb.model.LoginToken;

public class LoginDaoDBImpl implements LoginDao {

	@SuppressWarnings("unchecked")
	@Override
	public Login getUser(String userName, String password) {
		Transaction tx = null;
		Session session = null;
		List<Login> results = null;
		Login user = null;
		try {
			session = SessionFactoryGenerator.getSessionFactory().openSession();
			tx = session.beginTransaction();
			String userQuery = "select * from login where username=" + '\'' + userName + '\'' + " AND password=" + '\''
					+ password + '\'';
			SQLQuery query = session.createSQLQuery(userQuery);
			query.addEntity(Login.class);
			results = query.list();
			tx.commit();
			if (results.size() > 0) {
				user = results.get(0);
			}

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			addLogging(e.toString());
			SessionFactoryGenerator.shutdown();
		} 
		return user;
	}

	@Override
	public Login addUser(Login user) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public LoginToken getLoginToken(String token) {
		Transaction tx = null;
		Session session = null;
		List<LoginToken> results = null;
		LoginToken loginToken = null;
		String tokenQuery = null;
		tokenQuery = (token.length() == 0) ? "SELECT * from logintoken order by expiration_date desc limit 1"
				: "select * from \"logintoken\" where hash_token=\'" + token + '\'';
		try {
			session = SessionFactoryGenerator.getSessionFactory().openSession();
			tx = session.beginTransaction();
			SQLQuery query = session.createSQLQuery(tokenQuery);
			query.addEntity(LoginToken.class);
			results = query.list();
			tx.commit();
			if (results.size() > 0) {
				loginToken = results.get(0);

			}

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			addLogging(e.toString());
			SessionFactoryGenerator.shutdown();
		}
		return loginToken;
	}
	private void addLogging(String message) {
		Logger log = Logger.getLogger(EmployeeDaoDBImpl.class);
		log.error(message);
	}
	@SuppressWarnings("unchecked")
	@Override
	public int getUserId(String username) {
		Transaction tx = null;
		Session session = null;
		List<Object> results = null;
		int userId = 0;
		try {
			session = SessionFactoryGenerator.getSessionFactory().openSession();
			tx = session.beginTransaction();
			String userQuery = "select id from login where username=\'" + username + "\'";
			SQLQuery query = session.createSQLQuery(userQuery);
			results = query.list();
			tx.commit();
			if (results.size() > 0) {
				String userIdStr = results.get(0).toString();
				userId = Integer.parseInt(userIdStr);
			}

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			addLogging(e.toString());
			SessionFactoryGenerator.shutdown();
		}
		return userId;

	}

	@Override
	public void addLoginToken(LoginToken loginToken, Login login) {
		Transaction tx = null;
		Session session = null;

		try {
			session = SessionFactoryGenerator.getSessionFactory().openSession();
			tx = session.beginTransaction();
			session.saveOrUpdate(login);
			loginToken.setLogin(login);
			login.getLoginTokens().add(loginToken);
			session.saveOrUpdate(loginToken);
			if (!tx.wasCommitted()) {
			    tx.commit();
			}	
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			addLogging(e.toString());
			SessionFactoryGenerator.shutdown();
		}

	}

	@Override
	public void deleteLoginToken(List<LoginToken> tokens) {
		Transaction tx = null;
		Session session = null;
		

		try {
			session = SessionFactoryGenerator.getSessionFactory().openSession();
			tx = session.beginTransaction();
			for(LoginToken token: tokens) {
				Login login = token.getLogin();
				session.delete(token);
				session.delete(login);	
			}
			
			if (!tx.wasCommitted()) {
			    tx.commit();
			}
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			addLogging(e.toString());
			SessionFactoryGenerator.shutdown();
		} 
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LoginToken> getAllLoginTokens() {
		Transaction tx = null;
		Session session = null;
		List<LoginToken> results = null;
		String tokenQuery = null;
		tokenQuery = "SELECT * from logintoken";
			
		try {
			session = SessionFactoryGenerator.getSessionFactory().openSession();
			tx = session.beginTransaction();
			SQLQuery query = session.createSQLQuery(tokenQuery);
			query.addEntity(LoginToken.class);
			results = query.list();
			tx.commit();

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			addLogging(e.toString());
			SessionFactoryGenerator.shutdown();
		}
		return results;
	}

}
