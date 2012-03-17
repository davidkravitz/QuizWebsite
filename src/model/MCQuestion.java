package model;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class MCQuestion extends Question {
	private String prompt;
	private String correctAnswer;
	private String icOne;
	private String icTwo;
	private String icThree;
	public MCQuestion(String questionId, String prompt, String correctAnswer, String icOne, String icTwo, String icThree) {
		this.questionId = questionId;
		this.prompt = prompt;
		this.correctAnswer = correctAnswer;
		this.icOne = icOne;
		this.icTwo = icTwo;
		this.icThree = icThree;
	}
	
	@Override
	public String getPrompt() {
		return prompt;
	}

	public boolean checkAnswer(String userInput) {
		return userInput.equals(correctAnswer);
	}
	
	public String[] getAnswers() {
//		Set<String> answers = new HashSet<String>();
//		answers.add(correctAnswer);
//		answers.add(icOne);
//		answers.add(icTwo);
//		answers.add(icThree);
//		return (String[]) answers.toArray();
		String[] answers = new String[3];
		answers[0] = icOne;
		answers[1] = icTwo;
		answers[2] = icThree;
		return answers;
	}
	
	public static void createMCQuestion(int questionNumber, String prompt, String correctAnswer, String icAnswerOne, String icAnswerTwo, String icAnswerThree, int quizId) {
		String query = "INSERT into " + DBConnection.mcQuestionTable + " (questionId, question, correctAnswer, icAnswerOne, icAnswerTwo, icAnswerThree, quizId) VALUES ('MC_" + questionNumber + "', '" + prompt + "', '" + correctAnswer + "', '" + icAnswerOne + "', '" + icAnswerTwo + "', '" + icAnswerThree + "', '" + quizId + "')";
		try {
			DBConnection.newConnection().executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
