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

@WebFilter("/admin.jsp")
public class AdminFilter implements Filter {

	// URL location of the login entry screen
	private String loginURL = "login.jsp";

	public AdminFilter() {
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

			request.getRequestDispatcher("admin-login.html").forward(httpRequest, httpResponse);
		} else if (!User.isAdminUser(user.username)){
			request.getRequestDispatcher("admin-login.html").forward(httpRequest, httpResponse);
		} else {
			chain.doFilter(request,response);
		}
	}
}
