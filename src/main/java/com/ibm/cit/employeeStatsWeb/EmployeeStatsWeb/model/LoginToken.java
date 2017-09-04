package com.ibm.cit.employeeStatsWeb.EmployeeStatsWeb.model;

import java.util.Date;

public class LoginToken implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -798429852436638262L;
	private int loginTokenId;
	private String hashToken;
	private Date expirationDate;
	private Login login;

	public String getHashToken() {
		return hashToken;
	}

	public void setHashToken(String token) {
		this.hashToken = token;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public int getLoginTokenId() {
		return loginTokenId;
	}

	public void setLoginTokenId(int id) {
		this.loginTokenId = id;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

}
