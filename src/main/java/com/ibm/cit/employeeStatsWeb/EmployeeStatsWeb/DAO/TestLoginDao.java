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
		LoginToken loginToken = loginDao.getLoginToken("efa34bac-57c5-4414-99d1-f7509ac617a3");
		loginDao.deleteLoginToken(loginToken);
	}

}
