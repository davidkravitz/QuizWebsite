package model;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MCQuestion extends Question {
	private String prompt;
	private String correctAnswer;
	public MCQuestion(String questionId, String prompt, String correctAnswer) {
		this.questionId = questionId;
		this.prompt = prompt;
		this.correctAnswer = correctAnswer;
	}
	
	@Override
	public String getPrompt() {
		return prompt;
	}

	public boolean checkAnswer(String userInput) {
		return userInput.equals(correctAnswer);
	}
	
	public static void createMCQuestion(int questionNumber, String prompt, String correctAnswer, String icAnswerOne, String icAnswerTwo, String icAnswerThree, String quizName) {
		String query = "INSERT into " + DBConnection.mcQuestionTable + " (questionNumber, question, correctAnswer, icAnswerOne, icAnswerTwo, icAnswerThree, quizName) VALUES ('" + questionNumber + "', '" + prompt + "', '" + correctAnswer + "', '" + icAnswerOne + "', '" + icAnswerTwo + "', '" + icAnswerThree + "', '" + quizName + "')";
		try {
			DBConnection.newConnection().executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
