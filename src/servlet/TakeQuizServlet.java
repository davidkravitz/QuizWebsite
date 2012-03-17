package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Quiz;

/**
 * Servlet implementation class TakeQuizServlet
 */
@WebServlet("/take-quiz.jsp")
public class TakeQuizServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String multPageQuiz = "MultiplePageServlet";
	private static final String singlePageQuiz = "SinglePageServlet";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TakeQuizServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int quizId = Integer.valueOf(request.getParameter("quiz"));
		
		Quiz curr = Quiz.getQuiz(quizId);
		if(curr != null) {
			request.getSession().setAttribute("currentTakingQuiz", curr);
			if(curr.multiplePage) {
				request.getRequestDispatcher(multPageQuiz).forward(request, response);
			} else {
				request.getRequestDispatcher(singlePageQuiz).forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
