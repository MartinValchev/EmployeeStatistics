package com.ibm.cit.employeeStatsWeb.EmployeeStatsWeb.schedule;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.ibm.cit.employeeStatsWeb.EmployeeStatsWeb.DAO.LoginDao;
import com.ibm.cit.employeeStatsWeb.EmployeeStatsWeb.DAO.LoginDaoDBImpl;
import com.ibm.cit.employeeStatsWeb.EmployeeStatsWeb.model.LoginToken;

public class TokenCleanJob implements Job {

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		// TokenCleanJob takes the last record from login token table
		// compares its expiration date with the current one
		// if expiration date is before the current date remove the record from the
		// database.
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
		if (deleteList.size() > 0) {
			Logger log = Logger.getLogger(TokenCleanJob.class);
			log.info(deletedTokensString.toString());
			loginDao.deleteLoginToken(deleteList);
		}

	}

}
