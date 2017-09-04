package com.ibm.cit.employeeStatsWeb.EmployeeStatsWeb.DAO;

import com.ibm.cit.employeeStatsWeb.EmployeeStatsWeb.model.LoginToken;
import com.ibm.cit.employeeStatsWeb.EmployeeStatsWeb.model.Login;

public interface LoginDao {
	public Login getUser(String userName, String password);

	public Login addUser(Login user);

	public LoginToken getLoginToken(String token);

	public void addLoginToken(LoginToken userToken, Login login);

	public int getUserId(String username);

	public void deleteLoginToken(LoginToken token);
}
