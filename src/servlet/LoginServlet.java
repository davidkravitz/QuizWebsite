package servlet;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String errorUrl = "login-error.html";
	private static final String indexUrl = "index.jsp";
	
       
    public LoginServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Get the name and password entered by the user
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		// Check to see if the username and password are correct. If they are,
		// switch to the "user welcome" page. Otherwise, switch to the "please
		// try again page"
		if (User.userExists(username)) {
			if (User.correctPassword(username, password)) {
				HttpSession session = request.getSession();
				session.setAttribute("username", username);
				User user = User.getUser(username);
				session.setAttribute("user", user);
				
				// Redirect to the original request page if possible
//				String requestedUrl = (String) session.getAttribute("requestedUrl");
//				if (requestedUrl != null) {
//					System.out.println("Got here");
//					request.getRequestDispatcher(requestedUrl).forward(request, response);
//				} else  {
//					request.getRequestDispatcher(indexUrl).forward(request, response);
//				}
				
				// Send them to the home page
				request.getRequestDispatcher(indexUrl).forward(request, response);
				
			} else {
				request.getRequestDispatcher(errorUrl).forward(request, response);
			}
		} else {
			request.getRequestDispatcher(errorUrl).forward(request, response);
		}
	}

}