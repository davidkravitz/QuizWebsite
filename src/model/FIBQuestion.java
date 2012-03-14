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

	public static void createFIBQuestion(int questionNumber, String prompt, String correctAnswer, int quizId) {
		String query = "INSERT into " + DBConnection.fibQuestionTable + " (questionNumber, question, correctAnswer, quizId) VALUES ('FIB_" + questionNumber + "', '" + prompt + "', '" + correctAnswer + "', '" + quizId + "')";
		try {
			DBConnection.newConnection().executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
