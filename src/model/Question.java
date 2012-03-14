package model;

public class Question {
	protected String questionId;
	public String getQuestionType() {
		return questionId.substring(0, questionId.indexOf("_"));
	}
	public int getQuestionNumber() {
		return Integer.valueOf(questionId.substring(questionId.indexOf("_") + 1));
	}
	public String getPrompt() {
		return "";
	}
}
