package model;

import java.sql.SQLException;

public class TRQuestion extends Question {
	private String prompt;
	private String correctAnswer;
	
	public TRQuestion(String questionId, String prompt, String correctAnswer) {
		this.questionId = questionId;
		this.correctAnswer = correctAnswer;
		this.prompt = prompt;
	}

	@Override
	public String getPrompt() {
		return prompt;
	}

	public boolean checkAnswer(String userInput) {
		return userInput.equals(correctAnswer);
	}
	
	public static void createTRQuestion(int questionNumber, String prompt, String correctAnswer, int quizId) {
		String query = "INSERT into " + DBConnection.trQuestionTable + " (questionId, question, correctAnswer, quizId) VALUES ('TR_" + questionNumber + "', '" + prompt + "', '" + correctAnswer + "', '" + quizId + "')";
		try {
			DBConnection.newConnection().executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
