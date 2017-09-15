package com.ibm.cit.employeeStatsWeb.EmployeeStatsWeb.DAO;

import java.util.List;

import com.ibm.cit.employeeStatsWeb.EmployeeStatsWeb.model.Login;
import com.ibm.cit.employeeStatsWeb.EmployeeStatsWeb.model.LoginToken;

public interface LoginDao {
	/**
	 * get Login table entry using the provided username and password
	 * 
	 * @param String userName, String password
	 * @throws 
	 * @return  Login
	 *            
	 */
	public Login getUser(String userName, String password);
	/**
	 * inserts login table new entry for the input user provided 
	 * and returns the new Login entry added
	 * @param Login user
	 * @throws 
	 * @return  Login
	 *            
	 */
	public Login addUser(Login user);
	/**
	 * retrieve from LoginToken table specific entry by provided token
	 * @param String token
	 * @throws 
	 * @return  LoginToken
	 *            
	 */
	public LoginToken getLoginToken(String token);
	/**
	 * updates both Login and LoginToken table with provided LoginToken userToken, Login login
	 * @param LoginToken userToken, Login login
	 * @throws 
	 * @return 
	 *            
	 */
	public void addLoginToken(LoginToken userToken, Login login);
	
	/**
	 * gets from Login table the last userId logged using provided username
	 * @param LoginToken userToken, Login login
	 * @throws 
	 * @return 
	 *            
	 */
	public int getUserId(String username);
	/**
	 * removes from LoginToken Login tables the records which matches provided Login token Pojo details
	 * @param LoginToken userToken
	 * @throws 
	 * @return 
	 *            
	 */
	public void deleteLoginToken(List<LoginToken> tokens);
	/**
	 * retrieves from LoginToken Login tables all the records
	 * @param 
	 * @throws 
	 * @return List<LoginToken>
	 *            
	 */
	public List<LoginToken> getAllLoginTokens();
}
