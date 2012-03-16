package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Quiz;

/**
 * Servlet implementation class CreateQuizServlet
 */
@WebServlet("/CreateQuizServlet")
public class CreateQuizServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String addQuestionUrl = "add-question.jsp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateQuizServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request.getRequestDispatcher(successUrl).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String quizName = request.getParameter("quizName");
		String description = request.getParameter("description");
		String[] randomizeOp = request.getParameterValues("randomize");
		String[] immediateOp = request.getParameterValues("immediate");
		String pageStyle = request.getParameter("page");
		String category = request.getParameter("category");
		String createdBy = (String) request.getSession().getAttribute("username");
		
		boolean randomized = randomizeOp == null ? false : true; 
		boolean immediateCorrection = immediateOp == null ? false : true;
		boolean multiplePage = pageStyle.equals("single") ? false : true;
		
//		System.out.println("Create quiz servlet!");
//		System.out.println("Quiz name:"+quizName);
//		System.out.println("Desc: "+description);
//		System.out.println("randomized: "+randomized);
//		System.out.println("category: "+category);
//		System.out.println("createdBy: "+createdBy);
//		System.out.println("multiple: "+multiplePage);
//		System.out.println("immediate: "+immediateCorrection);
//		System.out.println("multiplePage: "+multiplePage);
		
		int quizId = Quiz.createQuiz(quizName, category, description, createdBy, randomized, multiplePage, immediateCorrection);
		Quiz curr = Quiz.getQuiz(quizId);
		//	System.out.println("Just created a quiz! "+quizId);
		request.getSession().setAttribute("currCreationQuiz", curr);
		request.getSession().setAttribute("quizFinished", false);
		
		request.getRequestDispatcher(addQuestionUrl).forward(request, response);
	}

}
