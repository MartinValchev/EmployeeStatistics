package com.ibm.cit.employeeStatsWeb.EmployeeStatsWeb.DAO;

import java.util.ArrayList;
import java.util.List;

import com.ibm.cit.employeeStatsWeb.EmployeeStatsWeb.model.LoginToken;

public class TestLoginDao {
	public static void main(String[] args) {
		LoginDao loginDao= new LoginDaoDBImpl();
		List<LoginToken> loginTokens = new ArrayList<LoginToken>();
		LoginToken login1 = loginDao.getLoginToken("0739405c-7c37-42cb-b285-1f6b216f9d6d");
		LoginToken login2 = loginDao.getLoginToken("3515af9f-8f85-4519-850a-1b4d86e1c1e2");
		loginTokens.add(login1);
		loginTokens.add(login2);
		loginDao.deleteLoginToken(loginTokens);
		
	}
}
