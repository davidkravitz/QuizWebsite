package model;

public class Question {
	protected String questionId;
	public String getQuestionType() {
		return questionId.substring(0, questionId.indexOf("_"));
	}
	public String getPrompt() {
		return "";
	}
}
