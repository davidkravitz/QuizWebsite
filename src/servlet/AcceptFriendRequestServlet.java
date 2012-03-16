package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.FriendRequest;

@WebServlet("/AcceptFriendRequestServlet")
public class AcceptFriendRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AcceptFriendRequestServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestingName = request.getParameter("requestingName");
		String requestedName = (String) request.getSession().getAttribute("username");
		FriendRequest.acceptFriendRequest(requestedName, requestingName);
		request.setAttribute("successMessage", "You and " + requestingName + " are now friends.");
		request.getRequestDispatcher("message-sent.jsp").forward(request, response);
		
	}

}
