package model;

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
}
