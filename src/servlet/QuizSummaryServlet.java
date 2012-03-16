package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Quiz;
import model.User;

/**
 * Servlet implementation class QuizSummaryServlet
 */
@WebServlet("/QuizSummaryServlet")
public class QuizSummaryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String successUrl = "quiz-summary.jsp";
	private static final String errorUrl = "quiz-summary-error.jsp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuizSummaryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int quizId = Integer.valueOf(request.getParameter("quiz"));
		//Quiz curr = Quiz.getQuiz(quizId);
		Quiz curr = new Quiz(1, "Math", "Algebra I", "Sheldon", "This quiz is meant for Algebra 1 students", "2012-3-14", false, false, false);
		
		String username = (String)request.getSession().getAttribute("username");
		User user = (User) request.getSession().getAttribute("user");

		if(curr != null) {
			//System.out.println("Quiz desc: "+curr.description);
			request.setAttribute("quiz", curr);
			request.getRequestDispatcher(successUrl).forward(request, response);
		}
		else {
			request.getRequestDispatcher(errorUrl).forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
