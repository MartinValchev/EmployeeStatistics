package com.ibm.cit.employeeStatsWeb.EmployeeStatsWeb.resource;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;

import javax.ws.rs.CookieParam;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

import com.ibm.cit.employeeStatsWeb.EmployeeStatsWeb.StatisticsService.UserLoginService;
import com.ibm.cit.employeeStatsWeb.EmployeeStatsWeb.StatisticsService.UserLoginServiceImpl;
import com.ibm.cit.employeeStatsWeb.EmployeeStatsWeb.model.Login;

@Path("/login")
@Produces(MediaType.TEXT_PLAIN)
public class UserResource {

	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public Response getLoginResponse(@FormParam("username") String username, @FormParam("password") String password,
			@CookieParam("requested_page") Cookie pageCookie) throws URISyntaxException {
		Response response = null;
		String loginResponse = null;
		Login user = new Login();
		user.setUsername(username);
		UserLoginService loginService = new UserLoginServiceImpl();
		String passwordHash = loginService.generateMD5Hash(password);
		user.setPassword(passwordHash);
		loginResponse = loginService.isLoginSuccessful(user);
		if (loginResponse.equals("true")) {
			String token = loginService.generateTokenString();
			Date expirationDate = new Date();
			// set expiration time to 15 min
			expirationDate = new Date(expirationDate.getTime() + 15 * 60 * 1000);
			loginService.addLoginToken(token, expirationDate, user);
			NewCookie newToken_cookie = new NewCookie("token_id", token, "/", "localhost", 1, "token id cookie", 900,
					expirationDate, false, true);
			NewCookie newUser_cookie = new NewCookie("logged_user", username, "/", "localhost", 1, "username cookie",
					900, expirationDate, false, false);
			@SuppressWarnings("unused")
			GenericEntity<String> entity = new GenericEntity<String>(loginResponse) {
			};
			if (pageCookie != null) {
				String pageUrl = pageCookie.getValue();
				response = Response.seeOther(URI.create(pageUrl)).cookie(newToken_cookie).cookie(newUser_cookie)
						.build();
			} else {
				response = Response.seeOther(URI.create("http://localhost:8080/EmployeeStatsWeb/pages/home.html"))
						.cookie(newUser_cookie).cookie(newToken_cookie).build();
			}
			return response;
		} else {
			response = Response.seeOther(URI.create("http://localhost:8080/EmployeeStatsWeb/pages/login.html")).build();
			return response;
		}
	}

	@Path("/secured/home")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public Response getTokenResponse(@CookieParam("token_id") Cookie cookie) {
		Response response = null;
		if (cookie == null) {
			response = Response.status(401).build();
		} else {
			String tokenIdValue = cookie.getValue();
			UserLoginService loginService = new UserLoginServiceImpl();
			response = (loginService.isLoginValid(tokenIdValue)) ? response = Response.status(200).build()
					: Response.status(401).build();
		}
		return response;
	}

	@Path("/exit")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public Response logout(@CookieParam("token_id") Cookie cookie) {
		Response response = null;
		if (cookie != null) {
			String tokenValue = cookie.getValue();
			Date expirationDate = new Date();
			// set expiration time to 15 min
			expirationDate = new Date(0);
			NewCookie deletedTokencookie = new NewCookie("token_id", "deleted", "/", "localhost", 1, "token id cookie",
					-50, expirationDate, false, true);
			UserLoginService loginService = new UserLoginServiceImpl();
			loginService.deleteLoginToken(tokenValue);
			
			expirationDate = new Date(expirationDate.getTime() - 15 * 60 * 1000);
			response = Response.status(200).cookie(deletedTokencookie).build();

		} else {
			response = Response.status(200).build();
		}

		return response;
	}
}
