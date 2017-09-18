package com.ibm.cit.employeeStatsWeb.EmployeeStatsWeb.DAO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ibm.cit.employeeStatsWeb.EmployeeStatsWeb.model.LoginToken;

public class TestLoginDao {
	public static void main(String[] args) {
		LoginDao loginDao = new LoginDaoDBImpl();
		List<LoginToken> loginTokens = loginDao.getAllLoginTokens();
		List<LoginToken> deleteList = new ArrayList<LoginToken>();
		StringBuilder deletedTokensString = new StringBuilder();
		for (LoginToken token : loginTokens) {
			Date expirationDate = token.getExpirationDate();
			Date currentDate = new Date();
			if (expirationDate.before(currentDate)) {
				deletedTokensString.append("Deleted token id: " + token.getLoginTokenId() + ", tokenHash"
						+ token.getHashToken() + System.getProperty("line.separator"));
				deleteList.add(token);
			}
		}
		System.out.println(deletedTokensString.toString());
	}
}
