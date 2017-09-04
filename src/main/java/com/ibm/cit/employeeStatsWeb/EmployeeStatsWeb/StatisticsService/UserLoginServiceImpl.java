package com.ibm.cit.employeeStatsWeb.EmployeeStatsWeb.StatisticsService;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.UUID;

import com.ibm.cit.employeeStatsWeb.EmployeeStatsWeb.DAO.LoginDao;
import com.ibm.cit.employeeStatsWeb.EmployeeStatsWeb.DAO.LoginDaoDBImpl;
import com.ibm.cit.employeeStatsWeb.EmployeeStatsWeb.model.LoginToken;
import com.ibm.cit.employeeStatsWeb.EmployeeStatsWeb.model.Login;

public class UserLoginServiceImpl implements UserLoginService {

	@Override
	public String isLoginSuccessful(Login user) {
		String isLoginSuccessful = "false";
		LoginDao loginDao =new LoginDaoDBImpl();
		String inputUsername = user.getUsername();
		Login dbUser =null;
		dbUser = loginDao.getUser(inputUsername, user.getPassword());
	
		if (dbUser != null) {
			isLoginSuccessful = "true";
		}
		return isLoginSuccessful;
	}

	@Override
	public Login addUser(Login user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addLoginToken(String token, Date expirationDate,Login login) {
		// loginToken received does not have tokenString generated
		LoginToken loginToken=  new LoginToken();
		loginToken.setExpirationDate(expirationDate);
		loginToken.setHashToken(token);
		LoginDao loginDao =new LoginDaoDBImpl();
		loginDao.addLoginToken(loginToken,login);
		
	}
	public String generateMD5Hash(String password) {
		MessageDigest m =null;
		String hashtext ="";
		try {
			m = MessageDigest.getInstance("MD5");
			m.reset();
			m.update(password.getBytes());
			byte[] digest = m.digest();
			BigInteger bigInt = new BigInteger(1,digest);
			hashtext = bigInt.toString(16);
			while(hashtext.length() < 32 ){
			  hashtext = "0"+hashtext;
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return hashtext;
	
	};

	@Override
	public String generateTokenString() {		
		
		return UUID.randomUUID().toString();
	}

	@Override
	public int getUserId(String username) {
		LoginDao loginDao =new LoginDaoDBImpl();
		int userID = loginDao.getUserId(username);
		return userID;
	}

	@Override
	public boolean isLoginValid(String tokenValue) {
		boolean isTokenValid = false;
		LoginDao loginDao =new LoginDaoDBImpl();
		LoginToken loginToken = loginDao.getLoginToken(tokenValue);	
		if(loginToken !=null) {
			Date currentDate = new Date();
			Date tokenExpireDate =loginToken.getExpirationDate();
			if(tokenExpireDate.after(currentDate)) {
				isTokenValid =true;
			}else {
				loginDao.deleteLoginToken(loginToken);
			} 
			
		}
		// check if token exists in db 
		// check if token not expired
		return isTokenValid;
	}

	@Override
	public LoginToken getLoginToken(String token) {
		LoginDao loginDao =new LoginDaoDBImpl();
		LoginToken loginToken = loginDao.getLoginToken(token);
		return loginToken;	
	}


}
