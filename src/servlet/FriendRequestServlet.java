package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.FriendRequest;

@WebServlet("/FriendRequestServlet")
public class FriendRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public FriendRequestServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String friend = request.getParameter("username");
		String currentUser = (String) request.getSession().getAttribute("username");
		FriendRequest.acceptFriendRequest(friend, currentUser);
		request.setAttribute("successMessage", "Your friend request has successfully been sent.");
		request.getRequestDispatcher("message-sent.jsp").forward(request, response);
		
	}

}
