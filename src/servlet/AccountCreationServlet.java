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

@WebServlet("/AccountCreationServlet")
public class AccountCreationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AccountCreationServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Get a hold of the account manager
		ServletContext context = request.getServletContext();
		
		// Get the name and password entered by the user
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		// Check with the account manager and see if the username exists. If it does,
		// switch to the "account name in use" page. Otherwise, switch to the
		// "welcome" page.
		if (User.userExists(username)) {
			HttpSession session = request.getSession();
			User user = User.getUser(username);
			session.setAttribute("username", username);
			request.getRequestDispatcher("account-exists.jsp").forward(request, response);
		} else {
			User.createUser(username, password, "", "", "");
			HttpSession session = request.getSession();
			User user = User.getUser(username);
			session.setAttribute("username", username);
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		
	}

}
