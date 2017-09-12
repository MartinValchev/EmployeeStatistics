package com.ibm.cit.employeeStatsWeb.EmployeeStatsWeb.StatisticsService;

import java.util.Date;

import com.ibm.cit.employeeStatsWeb.EmployeeStatsWeb.model.LoginToken;
import com.ibm.cit.employeeStatsWeb.EmployeeStatsWeb.model.Login;

public interface UserLoginService {
	public String isLoginSuccessful(Login user);

	public Login addUser(Login user);

	public boolean isLoginValid(String tokenValue);

	public void addLoginToken(String token, Date expirationDate, Login login);

	public String generateTokenString();

	public int getUserId(String username);

	public String generateMD5Hash(String password);

	public LoginToken getLoginToken(String token);
	
	public void deleteLoginToken(String tokenString);

}
