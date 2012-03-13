package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;

/**
 * LoginFilter requires a User object to be present in the current Session.
 * If no User is present, the request is redirected to the login screen;
 * the originally requested url is stored, and successful logins will look for
 * the original request url and redirect there if present.
 * 
 * It is expected that most mapped urls requiring LoginFilter will also be
 * chained to an authorization request using AuthorizationFilter.
 */
@WebFilter("/index.jsp")
public class LoginFilter implements Filter {

	// URL location of the login entry screen
	private String loginURL = "login.jsp";

	public LoginFilter() {
		super();
	}
	
	public void init(FilterConfig arg0) throws ServletException {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, 
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		String username = (String) httpRequest.getSession().getAttribute("username");  
		User user = User.getUser(username);

		if (user == null) {

			httpRequest.setAttribute("errors", 
			"You must log in to access the page you requested.");

			// don't overwrite the original request path if already present
			String requestedUrl = (String) httpRequest.getSession().getAttribute("requestedUrl");
			if (requestedUrl != null) {
				httpRequest.getSession().setAttribute("requestedUrl", requestedUrl); 
			} else {   
				requestedUrl = httpRequest.getRequestURL().toString();
				if (httpRequest.getQueryString() != null) {
					requestedUrl = requestedUrl + "?" + httpRequest.getQueryString();
				}
				httpRequest.getSession().setAttribute("requestedUrl", requestedUrl);

			}

			request.getRequestDispatcher("login.jsp").forward(httpRequest, httpResponse);
		} else {
			// user is logged in; move on down the chain            
			chain.doFilter(request,response);
		}
	}
}
