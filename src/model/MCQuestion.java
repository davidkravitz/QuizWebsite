package model;

public class MCQuestion extends Question {
	private String prompt;
	private String correctAnswer;
	public MCQuestion(String questionId, String prompt, String correctAnswer) {
		this.questionId = questionId;
		this.prompt = prompt;
		this.correctAnswer = correctAnswer;
	}
	
	@Override
	public String getPrompt() {
		return prompt;
	}

	public boolean checkAnswer(String userInput) {
		return userInput.equals(correctAnswer);
	}
}
