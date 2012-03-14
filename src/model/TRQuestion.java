package model;

import java.sql.SQLException;

public class TRQuestion extends Question {
	private String prompt;
	private String correctAnswer;
	
	public TRQuestion(String questionId, String prompt, String correctAnswer) {
		this.correctAnswer = correctAnswer;
		this.prompt = prompt;
		this.questionId = questionId;
	}

	@Override
	public String getPrompt() {
		return prompt;
	}

	public boolean checkAnswer(String userInput) {
		return userInput.equals(correctAnswer);
	}
	
	public static void createTRQuestion(int questionNumber, String prompt, String correctAnswer, String quizName) {
		String query = "INSERT into " + DBConnection.trQuestionTable + " (questionNumber, question, correctAnswer, quizName) VALUES ('" + questionNumber + "', '" + prompt + "', '" + correctAnswer + "', '" + quizName + "')";
		try {
			DBConnection.newConnection().executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
