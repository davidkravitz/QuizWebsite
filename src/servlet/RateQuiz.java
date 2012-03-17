package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.QuizRating;

/**
 * Servlet implementation class RateQuiz
 */
@WebServlet("/RateQuiz")
public class RateQuiz extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RateQuiz() {
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
		String rating = request.getParameter("rating");
		String quizId = request.getParameter("quizId");
		String message = request.getParameter("message");
		QuizRating.rankQuiz(Integer.valueOf(quizId), username, Integer.valueOf(rating), message);
		request.setAttribute("successMessage", "You have successfully rated that quiz.");
		request.getRequestDispatcher("confirmation-non-admin.jsp").forward(request, response);
	}

}
