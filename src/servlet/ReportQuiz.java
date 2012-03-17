package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.QuizRating;
import model.QuizReport;

/**
 * Servlet implementation class ReportQuiz
 */
@WebServlet("/ReportQuiz")
public class ReportQuiz extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportQuiz() {
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
		String type = request.getParameter("type");
		String description = request.getParameter("description");
		String quizId = request.getParameter("quizId");
		QuizReport.reportQuiz(username, Integer.valueOf(quizId), description, type);
		request.setAttribute("successMessage", "You have successfully reported that quiz.");
		request.getRequestDispatcher("confirmation-non-admin.jsp").forward(request, response);
	}

}
