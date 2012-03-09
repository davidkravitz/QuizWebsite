package model;

import java.sql.SQLException;

public class FIBQuestion extends Question {
	private String prompt;
	private String correctAnswer;
	
	public FIBQuestion(String questionId, String prompt, String correctAnswer) {
		this.questionId = questionId;
		this.prompt = prompt;
		this.correctAnswer = correctAnswer;
	}

	@Override
	public String getPrompt() {
		return prompt;
	}
	
	public boolean checkAnswer(String userInput) {
		return userInput.equals(this.correctAnswer);
	}

	public static void createFIBQuestion(int questionNumber, String prompt, String correctAnswer, String quizName) {
		String query = "INSERT into " + DBConnection.fibQuestionTable + " (questionNumber, question, correctAnswer, quizName) VALUES ('" + questionNumber + "', '" + prompt + "', '" + correctAnswer + "', '" + quizName + "')";
		try {
			DBConnection.newConnection().executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
