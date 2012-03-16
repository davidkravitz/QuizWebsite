package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.corba.se.impl.protocol.giopmsgheaders.Message;

import model.FriendRequest;

/**
 * Servlet implementation class SendMessageServlet
 */
@WebServlet("/SendMessageServlet")
public class SendMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SendMessageServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// message sender is the current user
		String username = (String) request.getSession().getAttribute("username");
		String recipientName = request.getParameter("recipientName");
		String message = request.getParameter("message");
		model.Message.sendMessage(username, recipientName, message, "");
		System.out.println(username + " sent a message to " + recipientName);
		System.out.println("Message: " + message);
		
	}

}
