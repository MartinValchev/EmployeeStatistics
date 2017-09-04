package com.ibm.cit.employeeStatsWeb.EmployeeStatsWeb.StatisticsService;

import java.util.UUID;

import com.ibm.cit.employeeStatsWeb.EmployeeStatsWeb.DAO.LoginDao;
import com.ibm.cit.employeeStatsWeb.EmployeeStatsWeb.DAO.LoginDaoDBImpl;
import com.ibm.cit.employeeStatsWeb.EmployeeStatsWeb.model.LoginToken;

public class TestMD5 {

	public static void main(String[] args) {
		LoginDao loginDao = new LoginDaoDBImpl();
		//UUID.randomUUID().toString();
		LoginToken loginToken = loginDao.getLoginToken("bc5e2706da26d15de0d7185e0132357e");
	}

}
