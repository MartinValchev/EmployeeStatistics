package com.ibm.cit.employeeStatsWeb.EmployeeStatsWeb.filters;

import java.io.IOException;
import java.util.Map;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import com.ibm.cit.employeeStatsWeb.EmployeeStatsWeb.StatisticsService.UserLoginService;
import com.ibm.cit.employeeStatsWeb.EmployeeStatsWeb.StatisticsService.UserLoginServiceImpl;

@Provider
public class SecurityFilter implements ContainerRequestFilter {
	private static final String COOKIE_TOKEN_ID = "token_id";
	private static final String SECURED_URL_PREFIX = "secured";
	private static final Response ACCESS_DENIED = Response.status(Response.Status.UNAUTHORIZED).build();

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		if (requestContext.getUriInfo().getPath().contains(SECURED_URL_PREFIX)) {
			Map<String, Cookie> cookies = requestContext.getCookies();
			Cookie tokenCookie = null;
			tokenCookie = getTokenCookie(cookies);
			if (isTokenCookieValid(tokenCookie)) {
				return;
			}
			requestContext.abortWith(ACCESS_DENIED);

		} else {
			return;
		}
	}

	private Cookie getTokenCookie(Map<String, Cookie> cookies) {
		Cookie tokenCookie = null;
		if (cookies == null) {
			return tokenCookie;
		}
		for (Map.Entry<String, Cookie> entry : cookies.entrySet()) {
			Cookie currentCookie = entry.getValue();
			if (currentCookie.getName().equals(COOKIE_TOKEN_ID)) {
				tokenCookie = currentCookie;
				break;
			}
		}
		return tokenCookie;
	}

	private boolean isTokenCookieValid(Cookie tokenCookie) {
		boolean isValid = false;
		UserLoginService loginService = new UserLoginServiceImpl();
		isValid = (tokenCookie != null) ? (loginService.isLoginValid(tokenCookie.getValue())) : false;
		return isValid;
	}

}
