package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Challenge;
import model.QuizRating;

/**
 * Servlet implementation class ChallengeFriend
 */
@WebServlet("/ChallengeFriend")
public class ChallengeFriend extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChallengeFriend() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = (String) request.getSession().getAttribute("username");
		String friendName = request.getParameter("friendName");
		String quizId = request.getParameter("quizId");
		String quizName = request.getParameter("quizName");
		Challenge.challengeUser(username, friendName, Integer.valueOf(quizId), quizName);
		request.setAttribute("successMessage", "You have successfully challenged that user.");
		request.getRequestDispatcher("confirmation-non-admin.jsp").forward(request, response);
	}

}
