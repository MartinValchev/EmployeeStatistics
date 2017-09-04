package com.ibm.cit.employeeStatsWeb.EmployeeStatsWeb.DAO;

import java.util.Date;

import com.ibm.cit.employeeStatsWeb.EmployeeStatsWeb.model.LoginToken;

public class TestLoginDao {

	public static void main(String[] args) {
		/*
		LoginDao loginDao = new LoginDaoDBImpl();
		LoginToken loginToken = new LoginToken();
		loginToken.setHashToken("tttttttttt");
		Date date= new Date();
		loginToken.setExpirationDate(date);
		loginDao.addLoginToken(loginToken); */
		LoginDao loginDao = new LoginDaoDBImpl();
		LoginToken logintoken = loginDao.getLoginToken("d8611c34-3c55-40d8-89c4-6250be729980");
		loginDao.deleteLoginToken(logintoken);
	}

}
