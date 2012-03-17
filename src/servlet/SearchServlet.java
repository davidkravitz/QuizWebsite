package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SearchServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// message sender is the current user
		System.out.println("Got here");
		String username = (String) request.getSession().getAttribute("username");
		String recipientName = request.getParameter("recipientName");
		String message = request.getParameter("message");
		model.Message.sendMessage(username, recipientName, message, "");
		request.setAttribute("successMessage", "Your message has successfully been sent.");
		request.getRequestDispatcher("message-sent.jsp").forward(request, response);
	}

}
