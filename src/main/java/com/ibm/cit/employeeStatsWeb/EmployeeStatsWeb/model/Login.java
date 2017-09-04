package com.ibm.cit.employeeStatsWeb.EmployeeStatsWeb.model;

import java.util.HashSet;
import java.util.Set;

public class Login implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -868715818388442151L;
	private int loginId;
	private String username;
	private String password;
	private Set<LoginToken> loginTokens=new HashSet<LoginToken>(
			0);

	public int getLoginId() {
		return loginId;
	}
	public void setLoginId(int id) {
		this.loginId = id;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<LoginToken> getLoginTokens() {

		return this.loginTokens;
	}

	public void setLoginTokens(Set<LoginToken> loginTokens) {
		this.loginTokens = loginTokens;
	}

}
