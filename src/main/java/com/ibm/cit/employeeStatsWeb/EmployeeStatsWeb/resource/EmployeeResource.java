package com.ibm.cit.employeeStatsWeb.EmployeeStatsWeb.resource;

import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.log4j.Logger;

import com.ibm.cit.employeeStatsWeb.EmployeeStatsWeb.StatisticsService.EmployeeStatisticsService;
import com.ibm.cit.employeeStatsWeb.EmployeeStatsWeb.StatisticsService.EmployeeStatisticsServiceImpl;
import com.ibm.cit.employeeStatsWeb.EmployeeStatsWeb.model.Employee;

@Path("secured/employees")
@Consumes(MediaType.APPLICATION_JSON)
public class EmployeeResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEmployees(@QueryParam("start") int start, @QueryParam("size") int size) {
		EmployeeStatisticsService statisticsService = new EmployeeStatisticsServiceImpl();

		Response response = null;
		ResponseBuilder rb = null;

		if (start >= 0 & size > 0) {
			int employeeCount = statisticsService.getEmployeeListSize();
			List<Employee> shrinkedList = statisticsService.getPortionEmployeeList(start, size);
			GenericEntity<List<Employee>> shrinkEntity = new GenericEntity<List<Employee>>(shrinkedList) {
			};
			String employeeCountStr = "" + employeeCount;
			rb = addCookiesResponse("employee_count", employeeCountStr, Response.status(200).entity(shrinkEntity));
		} else {
			List<Employee> employees = statisticsService.getEmployees();
			GenericEntity<List<Employee>> entity = new GenericEntity<List<Employee>>(employees) {
			};
			rb = Response.ok(entity, MediaType.APPLICATION_JSON);

		}

		response = rb.build();
		return response;
	}

	private ResponseBuilder addCookiesResponse(String cookieName, String cookieValue, ResponseBuilder rb) {
		Date expirationDate = new Date();
		expirationDate = new Date(expirationDate.getTime() + 15 * 60 * 1000);
		NewCookie cookie = new NewCookie(cookieName, cookieValue, "/", "localhost", 1, "", 900, expirationDate, false,
				false);
		return rb.cookie(cookie);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{employeeId}")
	public Response getEmployee(@PathParam("employeeId") int employeeId) {
		EmployeeStatisticsService statisticsService = new EmployeeStatisticsServiceImpl();
		Employee employee = statisticsService.getEmpoyee(employeeId);
		Response response = null;
		if (employee == null) {
			Logger log = Logger.getLogger(EmployeeResource.class);
			log.error("Ivalid employee id number provided");
			response = Response.status(500).entity("Ivalid id number...").build();
		} else {
			GenericEntity<Employee> entity = new GenericEntity<Employee>(employee) {
			};
			response = Response.status(200).entity(entity).build();

		}

		return response;
	}
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/service={lenghtOfService}")
	public Response getEmployeeLength(@PathParam("lenghtOfService") int lenghtOfService) {
		EmployeeStatisticsService statisticsService = new EmployeeStatisticsServiceImpl();
		Employee employee = statisticsService.getEmpoyee(lenghtOfService);
		Response response = null;
		if (employee == null) {
			Logger log = Logger.getLogger(EmployeeResource.class);
			log.error("Ivalid employee length of service provided");
			response = Response.status(500).entity("Ivalid id number...").build();
		} else {
			GenericEntity<Employee> entity = new GenericEntity<Employee>(employee) {
			};
			response = Response.status(200).entity(entity).build();

		}

		return response;
	}
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/first_name={first_name}")
	public Response getEmployeeFirstName(@PathParam("first_name") String first_name) {
		EmployeeStatisticsService statisticsService = new EmployeeStatisticsServiceImpl();
		List<Employee> employee = statisticsService.getEmpoyeeFirstName(first_name);
		Response response = null;
		if (employee == null) {
			Logger log = Logger.getLogger(EmployeeResource.class);
			log.error("Ivalid employee length of service provided");
			response = Response.status(500).entity("Ivalid id number...").build();
		} else {
			GenericEntity<Employee> entity = new GenericEntity<Employee>(employee) {
			};
			response = Response.status(200).entity(entity).build();

		}

		return response;
	}

	@POST
	@Path("/add")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addEmployee(Employee employee) {
		Response response = null;
		EmployeeStatisticsService statisticsService = new EmployeeStatisticsServiceImpl();
		Employee resultEmpl = statisticsService.addEmployee(employee);

		if (employee == null) {
			Logger log = Logger.getLogger(EmployeeResource.class);
			log.error("Server error - Employee record not retrieved correctly");
			response = Response.status(500).entity("Employee record not retrieved correctly").build();
		} else {
			GenericEntity<Employee> entity = new GenericEntity<Employee>(resultEmpl) {
			};
			/*
			 * UserLoginService loginService = new UserLoginServiceImpl(); LoginToken
			 * lastLoginToken = loginService.getLoginToken(""); String tokenString =
			 * lastLoginToken.getHashToken(); Cookie cookieToken = new Cookie("token_id",
			 * tokenString); NewCookie newCookieToken = new NewCookie(cookieToken);
			 */
			response = Response.status(200).entity(entity).build();
		}

		return response;

	}

}
