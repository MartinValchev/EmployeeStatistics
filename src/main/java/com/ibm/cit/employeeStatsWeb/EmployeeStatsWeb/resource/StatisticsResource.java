package com.ibm.cit.employeeStatsWeb.EmployeeStatsWeb.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.ibm.cit.employeeStatsWeb.EmployeeStatsWeb.StatisticsService.EmployeeStatisticsService;
import com.ibm.cit.employeeStatsWeb.EmployeeStatsWeb.StatisticsService.EmployeeStatisticsServiceImpl;
import com.ibm.cit.employeeStatsWeb.EmployeeStatsWeb.model.EmployeeStatistics;

@Path("secured/statistics")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StatisticsResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getStatistics() {
		EmployeeStatisticsService statisticsService = new EmployeeStatisticsServiceImpl();
		EmployeeStatistics statistics = statisticsService.generateNewEmployeesStatistics();

		GenericEntity<EmployeeStatistics> entity = new GenericEntity<EmployeeStatistics>(statistics) {
		};
		/*UserLoginService loginService = new UserLoginServiceImpl();
		LoginToken lastLoginToken = loginService.getLoginToken("");
		String tokenString = lastLoginToken.getHashToken();
		Cookie cookieToken = new Cookie("token_id", tokenString);
		NewCookie newCookieToken = new NewCookie(cookieToken); */
		//Response response = Response.status(200).entity(entity).cookie(newCookieToken).build();
		Response response = Response.status(200).entity(entity).build();
		return response;
	}

}
