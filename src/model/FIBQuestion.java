package model;

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

}
