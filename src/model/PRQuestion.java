package model;

import java.sql.SQLException;

public class PRQuestion extends Question {
	private String prompt;
	private String correctAnswer;
	private String imageUrl;

	public PRQuestion(String questionId, String prompt, String correctAnswer, String imageUrl) {
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
	
	public static void createPRQuestion(int questionNumber, String prompt, String imageUrl, String correctAnswer, int quizId) {
		String query = "INSERT into " + DBConnection.prQuestionTable + " (questionId, question, imageUrl, correctAnswer, quizId) VALUES ('PR_" + questionNumber + "', '" + prompt + "', '" + imageUrl + "', '" + correctAnswer + "', '" + quizId + "')";
		try {
			DBConnection.newConnection().executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
