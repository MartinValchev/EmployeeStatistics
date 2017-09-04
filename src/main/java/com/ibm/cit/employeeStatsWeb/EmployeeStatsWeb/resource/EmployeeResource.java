package com.ibm.cit.employeeStatsWeb.EmployeeStatsWeb.resource;

import java.util.ArrayList;
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
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.ibm.cit.employeeStatsWeb.EmployeeStatsWeb.StatisticsService.EmployeeStatisticsService;
import com.ibm.cit.employeeStatsWeb.EmployeeStatsWeb.StatisticsService.EmployeeStatisticsServiceImpl;
import com.ibm.cit.employeeStatsWeb.EmployeeStatsWeb.model.Employee;

@Path("secured/employees")
@Consumes(MediaType.APPLICATION_JSON)
public class EmployeeResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEmployees(@QueryParam("markerId") int markerId, @QueryParam("start") int start,
			@QueryParam("size") int size) {
		EmployeeStatisticsService statisticsService = new EmployeeStatisticsServiceImpl();
		List<Employee> employees = statisticsService.getEmployees();
		Response response = null;
		ResponseBuilder rb = null;
		// Cooking add
		/*UserLoginService loginService = new UserLoginServiceImpl();
		LoginToken lastLoginToken = loginService.getLoginToken("");
		String tokenString = lastLoginToken.getHashToken();
		Cookie cookieToken = new Cookie("token_id", tokenString);
		NewCookie newCookieToken = new NewCookie(cookieToken); */
		if (markerId > 0) {
			List<Employee> shrinkedList = new ArrayList<>();
			for (Employee empl : employees) {
				if (empl.getId() >= markerId) {
					shrinkedList.add(empl);
				}
			}
			GenericEntity<List<Employee>> entity = new GenericEntity<List<Employee>>(shrinkedList) {
			};

			rb = Response.ok(entity, MediaType.APPLICATION_JSON);
		} else if (start > 0 & size > 0) {
			List<Employee> shrinkedList = new ArrayList<>();
			for (Employee empl : employees) {
				if (empl.getId() >= start) {
					if (shrinkedList.size() == size) {
						break;
					}
					shrinkedList.add(empl);
				}
			}
			GenericEntity<List<Employee>> shrinkEntity = new GenericEntity<List<Employee>>(shrinkedList) {
			};
			rb = Response.ok(shrinkEntity, MediaType.APPLICATION_JSON);

		} else {

			GenericEntity<List<Employee>> entity = new GenericEntity<List<Employee>>(employees) {
			};
			rb = Response.ok(entity, MediaType.APPLICATION_JSON);

		}

		response = rb.build();
		return response;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{employeeId}")
	public Response getEmployee(@PathParam("employeeId") int employeeId) {
		EmployeeStatisticsService statisticsService = new EmployeeStatisticsServiceImpl();
		Employee employee = statisticsService.getEmpoyee(employeeId);
		Response response = null;
		if (employee == null) {
			response = Response.status(500).entity("Ivalid id number...").build();
		} else {
			GenericEntity<Employee> entity = new GenericEntity<Employee>(employee) {
			};
		/*	UserLoginService loginService = new UserLoginServiceImpl();
			LoginToken lastLoginToken = loginService.getLoginToken("");
			String tokenString = lastLoginToken.getHashToken();
			Cookie cookieToken = new Cookie("token_id", tokenString);
			NewCookie newCookieToken = new NewCookie(cookieToken); */
			response = Response.status(200).entity(entity).build();

		}

		return response;
	}

	@POST
	@Path("secured/add")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addEmployee(Employee employee) {
		Response response = null;
		EmployeeStatisticsService statisticsService = new EmployeeStatisticsServiceImpl();
		Employee resultEmpl = statisticsService.addEmployee(employee);

		if (employee == null) {
			response = Response.status(500).entity("Employee record not retrieved correctly").build();
		} else {
			GenericEntity<Employee> entity = new GenericEntity<Employee>(resultEmpl) {
			};
		/*	UserLoginService loginService = new UserLoginServiceImpl();
			LoginToken lastLoginToken = loginService.getLoginToken("");
			String tokenString = lastLoginToken.getHashToken();
			Cookie cookieToken = new Cookie("token_id", tokenString);
			NewCookie newCookieToken = new NewCookie(cookieToken); */
			response = Response.status(200).entity(entity).build();
		}

		return response;

	}

}
