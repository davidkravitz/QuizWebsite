package servlet;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.FIBQuestion;
import model.MCQuestion;
import model.PRQuestion;
import model.Question;
import model.Quiz;
import model.QuizTake;
import model.TRQuestion;
import model.User;

/**
 * Servlet implementation class SinglePageServlet
 */
@WebServlet("/SinglePageServlet")
public class SinglePageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String singlePageUrl = "single-page-quiz.jsp";
	private static final String gradeUrl = "quiz-completed.jsp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SinglePageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Quiz quiz = (Quiz) request.getSession().getAttribute("currentTakingQuiz");
		if(quiz == null) System.out.println("YO NO QUIZ YET");
		request.setAttribute("currentTakingQuiz", quiz);
		Date start = new Date();
		request.getSession().setAttribute("startTime", start);
		request.getRequestDispatcher(singlePageUrl).forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("HI");
		Date end = new Date();
		Date start = (Date) request.getSession().getAttribute("startTime");
		
		long timeDiff = (end.getTime() - start.getTime()) / 1000;
		
		Quiz quiz = (Quiz) request.getSession().getAttribute("currentTakingQuiz");
		List<Question> questions = Quiz.getQuestionsForQuiz(quiz.quizId);
		User user = (User) request.getSession().getAttribute("user");
		
		int numRight = 0;
		for(Question q : questions) {
			String userAnswer = request.getParameter(String.valueOf(q.getQuestionNumber()));
			String type = q.getQuestionType();
			
			System.out.println("Prompt: "+q.getPrompt());
			System.out.println("user answer: "+userAnswer);
			if(type.equals("MC")) {
				MCQuestion mc = (MCQuestion) q;
				if(mc.checkAnswer(userAnswer))
					numRight++;
			} else if(type.equals("TR")) {
				TRQuestion tr = (TRQuestion) q;
				if(tr.checkAnswer(userAnswer))
					numRight++;
			} else if(type.equals("PR")) {
				PRQuestion pr = (PRQuestion) q;
				if(pr.checkAnswer(userAnswer))
					numRight++;
			} else if(type.equals("FIB")) {
				FIBQuestion fib = (FIBQuestion) q;
				if(fib.checkAnswer(userAnswer))
					numRight++;
			}
		}
		QuizTake.recordCompletedQuiz(quiz.quizId, quiz.name, user.username, numRight, String.valueOf(timeDiff));
		request.setAttribute("grade", String.valueOf(numRight));
		request.setAttribute("time", String.valueOf(timeDiff));
		request.getRequestDispatcher(gradeUrl).forward(request, response);
	}

}
