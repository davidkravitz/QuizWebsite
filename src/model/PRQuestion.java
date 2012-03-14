package model;

import java.sql.SQLException;

public class PRQuestion extends Question {
	private String prompt;
	private String correctAnswer;
	private String imageUrl;

	public PRQuestion(String imageUrl, String correctAnswer, String prompt, String questionId) {
		this.questionId = questionId;
		this.prompt = prompt;
		this.correctAnswer = correctAnswer;
		this.imageUrl = imageUrl;
	}

	@Override
	public String getPrompt() {
		return prompt;
	}
	
	public String getImageUrl() {
		return imageUrl;
	}

	public boolean checkAnswer(String userInput) {
		return userInput.equals(correctAnswer);
	}
	
	public static void createPRQuestion(int questionNumber, String prompt, String imageUrl, String correctAnswer, String quizName) {
		String query = "INSERT into " + DBConnection.prQuestionTable + " (questionNumber, question, imageUrl, correctAnswer, quizName) VALUES ('" + questionNumber + "', '" + prompt + "', '" + imageUrl + "', '" + correctAnswer + "', '" + quizName + "')";
		try {
			DBConnection.newConnection().executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
