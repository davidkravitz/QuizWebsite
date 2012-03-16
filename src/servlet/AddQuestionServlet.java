package servlet;

import java.io.IOException;
import java.util.ArrayList;

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
import model.TRQuestion;

/**
 * Servlet implementation class AddQuestionServlet
 */
@WebServlet("/AddQuestionServlet")
public class AddQuestionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String successUrl = "added-question.jsp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddQuestionServlet() {
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
		String question = request.getParameter("question");
		String questionType = request.getParameter("questionType");
		System.out.println("Question: "+question);
		System.out.println("Question type: "+questionType);
		
		Quiz curr = (Quiz) request.getSession().getAttribute("currCreationQuiz");
		ArrayList<Question> questions = Quiz.getQuestionsForQuiz(curr.quizId);
		int questionNum = questions.size() + 1;
		
		if(questionType.equals("MC")) {
			String answerChoice = request.getParameter("answerChoice");
			String answerA = request.getParameter("A");
			String answerB = request.getParameter("B");
			String answerC = request.getParameter("C");
//			System.out.println("Answer: "+answerChoice);
//			System.out.println("A: "+answerA);
//			System.out.println("B: "+answerB);
//			System.out.println("C: "+answerC);
			
			String correct;
			if(answerChoice.equals("A")) correct = answerA;
			else if(answerChoice.equals("B")) correct = answerB;
			else correct = answerC;
			
			MCQuestion.createMCQuestion(questionNum, question, correct, answerA, answerB, answerC, curr.quizId);
		} else if(questionType.equals("QR")) {
			String answer = request.getParameter("answer");
			TRQuestion.createTRQuestion(questionNum, question, answer, curr.quizId);
			request.getRequestDispatcher(successUrl).forward(request, response);
		} else if(questionType.equals("PR")) {
			String image = request.getParameter("picture");
			String answer = request.getParameter("answer");
			PRQuestion.createPRQuestion(questionNum, question, image, answer, curr.quizId);
		} else if(questionType.equals("FIB")) { 
			String answer = request.getParameter("answer");
			FIBQuestion.createFIBQuestion(questionNum, question, answer, curr.quizId);
		}
	}

}
